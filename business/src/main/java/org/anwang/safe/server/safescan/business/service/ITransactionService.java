package org.anwang.safe.server.safescan.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anwang.safe.server.safescan.repository.TransactionEntity;

public interface ITransactionService extends IService<TransactionEntity> {

    TransactionEntity getByHash(String hash);

}
