package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.IAddressPropService;
import org.anwang.safe.server.safescan.repository.AddressPropEntity;
import org.anwang.safe.server.safescan.repository.mappers.IAddressPropEntityMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class AddressPropServiceImpl extends ServiceImpl<IAddressPropEntityMapper , AddressPropEntity>
            implements IAddressPropService {



}
