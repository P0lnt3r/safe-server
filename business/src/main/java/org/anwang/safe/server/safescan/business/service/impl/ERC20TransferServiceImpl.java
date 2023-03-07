package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.IERC20TransferService;
import org.anwang.safe.server.safescan.repository.ERC20TransferEntity;
import org.anwang.safe.server.safescan.repository.mappers.IERC20TransferEntityMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class ERC20TransferServiceImpl extends ServiceImpl<IERC20TransferEntityMapper , ERC20TransferEntity>
            implements IERC20TransferService {

    @Override
    public Page<ERC20TransferEntity> pageByAddress(String address, Page<ERC20TransferEntity> page) {
        LambdaQueryWrapper<ERC20TransferEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ERC20TransferEntity::getFrom , address);
        queryWrapper.or();
        queryWrapper.eq(ERC20TransferEntity::getTo,address);
        return page(page , queryWrapper);
    }
}
