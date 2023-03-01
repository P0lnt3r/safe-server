package org.anwang.safe.server.safescan.controller.block;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.anwang.safe.server.framework.web.model.JSONResponseHelper;
import org.anwang.safe.server.framework.web.model.vo.PageResponseVO;
import org.anwang.safe.server.safescan.business.service.IBlockService;
import org.anwang.safe.server.safescan.controller.block.model.BlockPageQueryDTO;
import org.anwang.safe.server.safescan.controller.block.model.BlockQueryDTO;
import org.anwang.safe.server.safescan.controller.block.model.BlockVO;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@Api(tags = "区块")
@RestController
public class BlockController {

    @Autowired
    IBlockService blockService;

    @Autowired
    JSONResponseHelper jsonResponseHelper;

    @ApiOperation("区块列表")
    @PostMapping("/blocks")
    public PageResponseVO<BlockVO , BlockEntity> blocks(@RequestBody BlockPageQueryDTO dto) {
        dto.setOrderProp("number");
        dto.setOrderMode("desc");
        Page<BlockEntity> entityPage = dto.convertPage();
        blockService.page(entityPage);
        PageResponseVO pageVO = new PageResponseVO<BlockVO , BlockEntity>().from(entityPage , BlockVO.class);
        return pageVO;
    }

    @ApiOperation("获取区块信息")
    @PostMapping("/block")
    public BlockVO block(@RequestBody  BlockQueryDTO dto){
        BigInteger number = dto.getNumber();
        String hash = dto.getHash();
        if ( number != null ){
            BlockEntity entity = blockService.getByNumber(number);
            return new BlockVO().from(entity);
        }
        return null;
    }



}
