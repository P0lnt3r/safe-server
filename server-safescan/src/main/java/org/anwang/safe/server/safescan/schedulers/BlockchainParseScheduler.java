package org.anwang.safe.server.safescan.schedulers;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import org.anwang.safe.server.safescan.business.service.IBlockService;
import org.anwang.safe.server.safescan.context.BlockchainContext;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.anwang.safe.server.safescan.repository.EventLogEntity;
import org.anwang.safe.server.safescan.repository.TransactionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlockchainParseScheduler implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(BlockchainParseScheduler.class);

    Web3j web3j = BlockchainContext.web3j;

    private BigInteger currentBlockNumber;

    private BigInteger latestBlockNumber;

    @Autowired
    IBlockService blockService;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("[?]/[?] 区块解析任务初始化...");
        BlockEntity blockEntity = blockService.getSavedMaxHeight();
        currentBlockNumber = blockEntity == null ? new BigInteger("25511425") : blockEntity.getNumber().add(BigInteger.ONE);
        latestBlockNumber = web3j.ethBlockNumber().send().getBlockNumber();
        log.info("[{}]/[{}] 区块解析任务完成. 起始同步高度:{} , 当前链上最新高度:{}", currentBlockNumber, latestBlockNumber, currentBlockNumber, latestBlockNumber);
    }

//    @Scheduled(cron = "0/3 * * * * ?")
    public void loop() throws Exception {
        while (currentBlockNumber.compareTo(latestBlockNumber) < 1) {
            log.info("[{}]/[{}] 获取区块[{}]数据:ethGetBlockByNumber", currentBlockNumber, latestBlockNumber, currentBlockNumber);
            EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(currentBlockNumber), true)
                    .send().getBlock();
            final BigInteger timestamp = block.getTimestamp();
            final LocalDateTime time = DateUtil.toLocalDateTime(new Date(block.getTimestamp().longValue() * 1000));
            final List<TransactionEntity> transactionEntityList = new ArrayList<>();
            final List<EventLogEntity> eventLogEntityList = new ArrayList<>();
            block.getTransactions().parallelStream().forEach(transactionResult -> {
                Transaction transaction = (Transaction) transactionResult.get();
                TransactionEntity transactionEntity = new TransactionEntity();
                BeanUtil.copyProperties(transaction, transactionEntity);
                String txHash = transaction.getHash();

                boolean finished = false;
                while (!finished) {
                    try {
                        TransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(txHash).send().getTransactionReceipt().get();
                        BeanUtil.copyProperties(transactionReceipt, transactionEntity);
                        transactionEntity.setTimestamp(timestamp);
                        transactionEntity.setTime(time);
                        transactionEntityList.add(transactionEntity);
                        // Event Log
                        List<Log> logs = transactionReceipt.getLogs();
                        logs.forEach(eventLog -> {
                            EventLogEntity eventLogEntity = new EventLogEntity();
                            BeanUtil.copyProperties(eventLog, eventLogEntity);
                            if (eventLog.getTopics() != null && !eventLog.getTopics().isEmpty()) {
                                String topic0 = eventLog.getTopics().get(0);
                                eventLogEntity.setTopic0(topic0);
                                String topicsArrJson = JSONUtil.toJsonStr(eventLog.getTopics());
                                eventLogEntity.setTopicsArr(topicsArrJson);
                            }
                            eventLogEntity.setTime(time);
                            eventLogEntity.setTimestamp(timestamp);
                            eventLogEntityList.add(eventLogEntity);
                        });
                    } catch (Exception e) {
                        finished = false;
                        e.printStackTrace();
                        log.info("[{}]/[{}] TransactionReceipt-{} 获取异常，重新获取..", currentBlockNumber, latestBlockNumber, txHash);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        continue;
                    }
                    finished = true;
                }
            });
            BlockEntity blockEntity = new BlockEntity();
            BeanUtil.copyProperties(block, blockEntity);
            blockEntity.setTime(time);
            blockEntity.setTxns(block.getTransactions().size());

            // 做一次排序
            List<TransactionEntity> sortedTransactionEntityList = transactionEntityList.stream()
                    .sorted(Comparator.comparing(TransactionEntity::getTransactionIndex))
                    .collect(Collectors.toList());
            List<EventLogEntity> sortedEventLogEntityList = eventLogEntityList.stream()
                    .sorted(Comparator.comparing(EventLogEntity::getTransactionIndex))
                    .collect(Collectors.toList());

            blockService.saveBatchBlockDetails(blockEntity, sortedTransactionEntityList, sortedEventLogEntityList);
            log.info("[{}]/[{}] 保存区块[{}]内的交易信息[ txns:{} , eventLogs:{} ]",
                    currentBlockNumber, latestBlockNumber, blockEntity.getNumber(),
                    transactionEntityList.size(),
                    eventLogEntityList.size()
            );
            currentBlockNumber = currentBlockNumber.add(BigInteger.ONE);
        }
    }


}
