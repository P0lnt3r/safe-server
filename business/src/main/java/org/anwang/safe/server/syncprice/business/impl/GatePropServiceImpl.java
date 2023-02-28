package org.anwang.safe.server.syncprice.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.syncprice.business.IGatePropService;
import org.anwang.safe.server.syncprice.repository.GatePropEntity;
import org.anwang.safe.server.syncprice.repository.mappers.IGatePropEntityMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional( readOnly = true)
public class GatePropServiceImpl extends ServiceImpl<IGatePropEntityMapper , GatePropEntity>
    implements IGatePropService {



}
