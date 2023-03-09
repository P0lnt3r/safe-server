package org.anwang.safe.server.safescan.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anwang.safe.server.safescan.repository.AddressAbiEntity;

import java.util.List;

public interface IAddressAbiService extends IService<AddressAbiEntity> {

    List<AddressAbiEntity> getByAddress( String... addresses );

}
