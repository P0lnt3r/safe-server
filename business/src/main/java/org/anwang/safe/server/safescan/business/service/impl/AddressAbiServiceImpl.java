package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.repository.AddressAbiEntity;
import org.anwang.safe.server.safescan.repository.mappers.IAddressAbiMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class AddressAbiServiceImpl extends ServiceImpl<IAddressAbiMapper , AddressAbiEntity> {
}
