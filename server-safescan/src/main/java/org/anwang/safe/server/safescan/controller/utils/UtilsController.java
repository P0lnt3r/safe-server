package org.anwang.safe.server.safescan.controller.utils;

import io.swagger.annotations.Api;
import org.anwang.safe.server.safescan.business.service.IAbiMethodSignatureService;
import org.anwang.safe.server.safescan.business.service.IAddressAbiService;
import org.anwang.safe.server.safescan.controller.utils.model.AbiMethodSignatureVO;
import org.anwang.safe.server.safescan.controller.utils.model.AddressAbiVO;
import org.anwang.safe.server.safescan.controller.utils.model.QueryAddressAbiDTO;
import org.anwang.safe.server.safescan.repository.AddressAbiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Utils")
@RestController()
public class UtilsController {

    @Autowired
    IAbiMethodSignatureService abiMethodSignatureService;

    @Autowired
    IAddressAbiService addressAbiService;

    @GetMapping("/utils/abi_method_signature")
    public List<AbiMethodSignatureVO> listAbiMethodSignature(){
        return abiMethodSignatureService.list().stream()
                .map( abiMethodSignatureEntity -> (AbiMethodSignatureVO)new AbiMethodSignatureVO().from(abiMethodSignatureEntity) )
                .collect(Collectors.toList());
    }

    @PostMapping("/utils/getabi")
    public List<AddressAbiVO> getAbi(@RequestBody QueryAddressAbiDTO dto){
        List<AddressAbiEntity> list = addressAbiService.getByAddress(dto.getAddresses());
        return list.stream().map( addressAbiEntity -> {
            return (AddressAbiVO)new AddressAbiVO().from(addressAbiEntity);
        } ).collect(Collectors.toList());
    }

}
