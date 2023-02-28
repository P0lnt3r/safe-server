package org.anwang.safe.server.safescan.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anwang.safe.server.safescan.repository.EventLogEntity;

import java.util.List;

public interface IEventLogService extends IService<EventLogEntity> {

    List<EventLogEntity> listByTxHash(String txHash);

}
