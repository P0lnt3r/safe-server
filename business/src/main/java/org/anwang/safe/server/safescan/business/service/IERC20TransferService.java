package org.anwang.safe.server.safescan.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.anwang.safe.server.safescan.repository.ERC20TransferEntity;

public interface IERC20TransferService extends IService<ERC20TransferEntity> {

    Page<ERC20TransferEntity> pageByAddress( String address , Page<ERC20TransferEntity> page );

}
