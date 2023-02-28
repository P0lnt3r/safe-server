package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.IEventLogService;
import org.anwang.safe.server.safescan.repository.EventLogEntity;
import org.anwang.safe.server.safescan.repository.mappers.IEventLogEntityMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
@Transactional
public class EventLogServiceImpl extends ServiceImpl<IEventLogEntityMapper , EventLogEntity>
        implements IEventLogService {

    @Override
    public List<EventLogEntity> listByTxHash(String txHash) {
        LambdaQueryWrapper<EventLogEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(EventLogEntity::getTransactionHash , txHash);
        queryWrapper.orderByAsc( EventLogEntity::getLogIndex  );
        return list(queryWrapper);
    }
}
