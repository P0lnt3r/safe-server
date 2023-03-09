package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.IAddressAbiService;
import org.anwang.safe.server.safescan.repository.AddressAbiEntity;
import org.anwang.safe.server.safescan.repository.mappers.IAddressAbiMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Primary
public class AddressAbiServiceImpl extends ServiceImpl<IAddressAbiMapper , AddressAbiEntity>
        implements IAddressAbiService {

    @Override
    public List<AddressAbiEntity> getByAddress(String... addresses) {
        LambdaQueryWrapper<AddressAbiEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in( AddressAbiEntity::getAddress , addresses );
        return list(queryWrapper);
    }

}
