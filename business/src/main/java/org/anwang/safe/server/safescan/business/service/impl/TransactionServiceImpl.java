package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.ITransactionService;
import org.anwang.safe.server.safescan.repository.TransactionEntity;
import org.anwang.safe.server.safescan.repository.mappers.ITransactionEntityMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Primary
@Transactional
public class TransactionServiceImpl extends ServiceImpl<ITransactionEntityMapper , TransactionEntity>
        implements ITransactionService {

    @Override
    public TransactionEntity getByHash(String hash) {
        LambdaQueryWrapper<TransactionEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TransactionEntity::getHash , hash);
        List<TransactionEntity> list = list(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Page<TransactionEntity> pageByAddress(String address , Page page) {
        LambdaQueryWrapper<TransactionEntity> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(TransactionEntity::getFrom , address);
        queryWrapper.or();
        queryWrapper.eq(TransactionEntity::getTo,address);

        return page(page , queryWrapper);
    }

    @Override
    public Page<TransactionEntity> pageByBlockNumber(BigInteger blockNumber, Page page) {
        LambdaQueryWrapper<TransactionEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TransactionEntity::getBlockNumber , blockNumber);
        return page(page , queryWrapper);
    }
}
