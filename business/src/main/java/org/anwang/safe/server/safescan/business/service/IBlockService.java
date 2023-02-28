package org.anwang.safe.server.safescan.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.anwang.safe.server.safescan.repository.EventLogEntity;
import org.anwang.safe.server.safescan.repository.TransactionEntity;

import java.math.BigInteger;
import java.util.List;

public interface IBlockService extends IService<BlockEntity> {

    /**
     * 获取数据库存储的最新区块数据
     * @return
     */
    BlockEntity getSavedMaxHeight();

    void saveBatchBlockDetails(
            BlockEntity blockEntity,
            List<TransactionEntity> transactionEntityList,
            List<EventLogEntity> eventLogEntityList
    );

    BlockEntity getByNumber(BigInteger number);

    BlockEntity getByHash(String hash);

}
