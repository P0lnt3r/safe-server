package org.anwang.safe.server.safescan.controller.utils;

import io.swagger.annotations.Api;
import org.anwang.safe.server.safescan.business.service.IAbiMethodSignatureService;
import org.anwang.safe.server.safescan.controller.utils.model.AbiMethodSignatureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Utils")
@RestController()
public class UtilsController {

    @Autowired
    IAbiMethodSignatureService abiMethodSignatureService;

    @GetMapping("/utils/abi_method_signature")
    public List<AbiMethodSignatureVO> listAbiMethodSignature(){
        return abiMethodSignatureService.list().stream()
                .map( abiMethodSignatureEntity -> (AbiMethodSignatureVO)new AbiMethodSignatureVO().from(abiMethodSignatureEntity) )
                .collect(Collectors.toList());
    }

}
