package org.anwang.safe.server.safescan.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.anwang.safe.server.safescan.business.service.IAbiMethodSignatureService;
import org.anwang.safe.server.safescan.repository.AbiMethodSignatureEntity;
import org.anwang.safe.server.safescan.repository.mappers.IAbiMethodSignatureMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class AbiMethodSignatureServiceImpl extends ServiceImpl<IAbiMethodSignatureMapper , AbiMethodSignatureEntity>
            implements IAbiMethodSignatureService {



}
