package org.anwang.safe.server.safescan.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.anwang.safe.server.safescan.repository.TransactionEntity;

import java.math.BigInteger;
import java.util.List;

public interface ITransactionService extends IService<TransactionEntity> {

    TransactionEntity getByHash(String hash);

    Page<TransactionEntity> pageByBlockNumber(BigInteger blockNumber , Page page);

    Page<TransactionEntity> pageByAddress( String address , Page page );

    List<TransactionEntity> getLatest(Integer size);

}
