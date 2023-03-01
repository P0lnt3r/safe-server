package org.anwang.safe.server.safescan.controller.tx.model;

import io.swagger.annotations.ApiModelProperty;
import org.anwang.safe.server.framework.web.model.dto.PageQueryDTO;
import org.anwang.safe.server.safescan.repository.TransactionEntity;

import java.math.BigInteger;

public class TransactionPageQueryDTO extends PageQueryDTO<TransactionEntity> {

    @ApiModelProperty("区块高度")
    private BigInteger blockNumber;

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }
}
