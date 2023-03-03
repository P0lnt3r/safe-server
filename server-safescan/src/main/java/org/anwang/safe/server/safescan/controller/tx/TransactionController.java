package org.anwang.safe.server.safescan.controller.tx;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.anwang.safe.server.framework.web.model.JSONResponseHelper;
import org.anwang.safe.server.framework.web.model.vo.PageResponseVO;
import org.anwang.safe.server.safescan.business.service.IBlockService;
import org.anwang.safe.server.safescan.business.service.ITransactionService;
import org.anwang.safe.server.safescan.controller.block.model.BlockPageQueryDTO;
import org.anwang.safe.server.safescan.controller.block.model.BlockVO;
import org.anwang.safe.server.safescan.controller.tx.model.TransactionPageQueryDTO;
import org.anwang.safe.server.safescan.controller.tx.model.TransactionQueryDTO;
import org.anwang.safe.server.safescan.controller.tx.model.TransactionVO;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.anwang.safe.server.safescan.repository.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "交易")
@RestController
public class TransactionController {

    @Autowired
    ITransactionService transactionService;

    @Autowired
    JSONResponseHelper jsonResponseHelper;

    @ApiOperation("交易列表")
    @PostMapping("/txs")
    public PageResponseVO<TransactionVO, TransactionEntity> blocks(@RequestBody TransactionPageQueryDTO dto) {
        Page<TransactionEntity> entityPage = dto.convertPage();
        if ( dto.getBlockNumber() != null ){
            transactionService.pageByBlockNumber(dto.getBlockNumber() , entityPage);
        }else{
            transactionService.page(entityPage);
        }
        PageResponseVO<TransactionVO, TransactionEntity> pageVO = new PageResponseVO<TransactionVO, TransactionEntity>().from(entityPage , TransactionVO.class);
        return pageVO;
    }

    @ApiOperation("地址交易列表")
    @PostMapping("/txs/address")
    public PageResponseVO<TransactionVO , TransactionEntity> addressTransactions( @RequestBody TransactionPageQueryDTO dto ){
        String address = dto.getAddress();
        Page<TransactionEntity> entityPage = dto.convertPage();
        transactionService.pageByAddress(address , entityPage);
        return new PageResponseVO<TransactionVO, TransactionEntity>().from(entityPage , TransactionVO.class);
    }

    @ApiOperation("交易信息")
    @PostMapping("/tx")
    public TransactionVO blocks(@RequestBody TransactionQueryDTO dto) {
        String hash = dto.getHash();
        TransactionEntity transactionEntity = transactionService.getByHash(hash);
        return new TransactionVO().from(transactionEntity);
    }


}
