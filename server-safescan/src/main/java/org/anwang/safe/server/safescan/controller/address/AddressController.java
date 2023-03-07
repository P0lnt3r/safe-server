package org.anwang.safe.server.safescan.controller.address;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.anwang.safe.server.framework.web.model.vo.PageResponseVO;
import org.anwang.safe.server.safescan.business.service.IAddressPropService;
import org.anwang.safe.server.safescan.context.BlockchainContext;
import org.anwang.safe.server.safescan.controller.address.model.AddressPropVO;
import org.anwang.safe.server.safescan.controller.block.model.BlockPageQueryDTO;
import org.anwang.safe.server.safescan.controller.block.model.BlockVO;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "地址")
@RestController
public class AddressController {

    @Autowired
    IAddressPropService addressPropService;

    @Autowired
    BlockchainContext blockchainContext;

    @ApiOperation("TEST")
    @GetMapping("/addresses/context")
    public String test(){
        return blockchainContext.serialize();
    }

    @ApiOperation("区块列表")
    @PostMapping("/addresses/prop")
    public List<AddressPropVO> getAllAddressProp() {
        return addressPropService.list()
                .stream()
                .map( entity -> {
                    AddressPropVO vo = new AddressPropVO();
                    vo.from(entity);
                    return vo;
                })
                .collect(Collectors.toList());
    }

}
