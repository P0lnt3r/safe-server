package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.IBlockService;
import org.anwang.safe.server.safescan.business.service.IERC20TransferService;
import org.anwang.safe.server.safescan.business.service.IEventLogService;
import org.anwang.safe.server.safescan.business.service.ITransactionService;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.anwang.safe.server.safescan.repository.ERC20TransferEntity;
import org.anwang.safe.server.safescan.repository.EventLogEntity;
import org.anwang.safe.server.safescan.repository.TransactionEntity;
import org.anwang.safe.server.safescan.repository.mappers.IBlockEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class BlockServiceImpl extends ServiceImpl<IBlockEntityMapper, BlockEntity>
        implements IBlockService {

    @Autowired
    IEventLogService eventLogService;
    @Autowired
    ITransactionService transactionService;
    @Autowired
    IERC20TransferService erc20TransferService;

    @Override
    @Transactional(readOnly = true)
    public BlockEntity getSavedMaxHeight() {
        LambdaQueryWrapper<BlockEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(BlockEntity::getNumber);
        queryWrapper.last("limit 1");

        List<BlockEntity> list = list(queryWrapper);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public BlockEntity getByNumber(BigInteger number) {
        LambdaQueryWrapper<BlockEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BlockEntity::getNumber, number);
        List<BlockEntity> list = list(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public BlockEntity getByHash(String hash) {
        LambdaQueryWrapper<BlockEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(BlockEntity::getHash, hash);
        List<BlockEntity> list = list(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void saveBatchBlockDetails(
            BlockEntity blockEntity,
            List<TransactionEntity> transactionEntityList,
            List<EventLogEntity> eventLogEntityList,
            List<ERC20TransferEntity> erc20TransferEntityList
    ) {
        this.save(blockEntity);
        transactionService.saveBatch(transactionEntityList);
        eventLogService.saveBatch(eventLogEntityList);
        erc20TransferService.saveBatch(erc20TransferEntityList);
    }

}
