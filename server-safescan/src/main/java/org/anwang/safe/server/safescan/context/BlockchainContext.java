package org.anwang.safe.server.safescan.context;

import cn.hutool.json.JSONUtil;
import io.reactivex.internal.util.LinkedArrayList;
import jnr.ffi.annotations.In;
import org.anwang.safe.server.safescan.business.service.IBlockService;
import org.anwang.safe.server.safescan.business.service.ITransactionService;
import org.anwang.safe.server.safescan.controller.block.model.BlockVO;
import org.anwang.safe.server.safescan.controller.tx.model.TransactionVO;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.anwang.safe.server.safescan.repository.TransactionEntity;
import org.anwang.safe.server.safescan.websocket.BlockchainContextWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Component
public class BlockchainContext implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(BlockchainContext.class);

    private static final Integer DEFAULT_QUEUE_SIZE = 10;

    @Value("${web3j.endpoint}")
    private String endpoint;

    public static Web3j web3j;

    public static BlockchainContext instance ;

    @Autowired
    private IBlockService blockService;

    @Autowired
    private ITransactionService transactionService;

    private LinkedList<BlockEntity> blockQueue;

    private LinkedList<TransactionEntity> transactionQueue;

    public BigInteger latestBlockNumber;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            initializeWeb3jClient();
        }catch (Exception e){
            throw e;
        }
        initializeQueue();
        instance = this;
    }

    private void initializeWeb3jClient() throws Exception {
        log.info("初始化 Web3j 客户端 , endpoint = {}" , endpoint);
        HttpService service = new HttpService( endpoint );
        web3j = Web3j.build(service);
        String version = web3j.web3ClientVersion().send().getWeb3ClientVersion();
        log.info("初始化 Web3j 客户端完成 , client-version = {}" , version);
    }

    private void initializeQueue(){
        List<BlockEntity> latestBlockList = blockService.getLatest(DEFAULT_QUEUE_SIZE);
        blockQueue = new LinkedList<>();
        blockQueue.addAll(latestBlockList);
        log.info("初始化区块数据队列 = {}" , blockQueue.size());
        latestBlockNumber = blockQueue.isEmpty() ? BigInteger.ZERO : blockQueue.getFirst().getNumber();
        List<TransactionEntity> latestTransactionList = transactionService.getLatest(DEFAULT_QUEUE_SIZE);
        transactionQueue = new LinkedList<>();
        transactionQueue.addAll(latestTransactionList);
        log.info("初始化交易数据队列 = {}" , transactionQueue.size());
    }

    public synchronized void updateBlockAndTransactionQueue(
            BlockEntity blockEntity,
            List<TransactionEntity> transactionEntityList
    ){
        if ( blockQueue.size() == DEFAULT_QUEUE_SIZE ){
            blockQueue.removeLast();
        }
        blockQueue.addFirst(blockEntity);
        latestBlockNumber = blockEntity.getNumber();
        transactionEntityList.stream()
                .sorted(Comparator.comparing(TransactionEntity::getTransactionIndex))
                .limit(DEFAULT_QUEUE_SIZE)
                .sorted(( t1 , t2 ) -> t2.getTransactionIndex().compareTo(t1.getTransactionIndex()))
                .forEach( transactionEntity -> {
                    if ( transactionQueue.size() == DEFAULT_QUEUE_SIZE ){
                        transactionQueue.removeLast();
                    }
                    transactionQueue.addFirst(transactionEntity);
                });


        try {
            BlockchainContextWebSocket.send(serialize());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String serialize(){
        BlockchainContextVO vo = new BlockchainContextVO();
        vo.setLatestBlockNumber(latestBlockNumber);
        vo.setLatestBlocks(
                blockQueue.stream()
                    .map( blockEntity -> (BlockVO)new BlockVO().from(blockEntity))
                    .collect(Collectors.toList())
        );
        vo.setLatestTransactions(
                transactionQueue.stream()
                    .map( transactionEntity -> (TransactionVO) new TransactionVO().from(transactionEntity) )
                    .collect(Collectors.toList())
        );
        return JSONUtil.toJsonStr(vo);
    }

    public static class BlockchainContextVO {

        private BigInteger latestBlockNumber;

        private List<BlockVO> latestBlocks;

        private List<TransactionVO> latestTransactions;

        public BigInteger getLatestBlockNumber() {
            return latestBlockNumber;
        }

        public void setLatestBlockNumber(BigInteger latestBlockNumber) {
            this.latestBlockNumber = latestBlockNumber;
        }

        public List<BlockVO> getLatestBlocks() {
            return latestBlocks;
        }

        public void setLatestBlocks(List<BlockVO> latestBlocks) {
            this.latestBlocks = latestBlocks;
        }

        public List<TransactionVO> getLatestTransactions() {
            return latestTransactions;
        }

        public void setLatestTransactions(List<TransactionVO> latestTransactions) {
            this.latestTransactions = latestTransactions;
        }
    }

}
