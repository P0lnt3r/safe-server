package org.anwang.safe.server.safescan.controller.tx;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.anwang.safe.server.framework.web.model.vo.PageResponseVO;
import org.anwang.safe.server.safescan.business.service.IERC20TransferService;
import org.anwang.safe.server.safescan.controller.tx.model.ERC20TransferPageQueryDTO;
import org.anwang.safe.server.safescan.controller.tx.model.ERC20TransferVO;
import org.anwang.safe.server.safescan.controller.tx.model.TransactionPageQueryDTO;
import org.anwang.safe.server.safescan.controller.tx.model.TransactionVO;
import org.anwang.safe.server.safescan.repository.ERC20TransferEntity;
import org.anwang.safe.server.safescan.repository.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "ERC20交易")
@RestController
public class ERC20TransferController {

    @Autowired
    IERC20TransferService erc20TransferService;

    @ApiOperation("交易列表")
    @PostMapping("/txs/erc20")
    public PageResponseVO<ERC20TransferVO, ERC20TransferEntity> blocks(@RequestBody ERC20TransferPageQueryDTO dto) {
        Page<ERC20TransferEntity> entityPage = dto.convertPage();
        String address = dto.getAddress();
        erc20TransferService.pageByAddress(address , entityPage);
        PageResponseVO<ERC20TransferVO, ERC20TransferEntity> pageVO = new PageResponseVO<ERC20TransferVO, ERC20TransferEntity>().from(entityPage , ERC20TransferVO.class);
        return pageVO;
    }



}
