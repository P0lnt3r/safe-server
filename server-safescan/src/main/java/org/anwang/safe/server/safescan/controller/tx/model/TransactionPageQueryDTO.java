package org.anwang.safe.server.safescan.controller.tx.model;

import io.swagger.annotations.ApiModelProperty;
import org.anwang.safe.server.framework.web.model.dto.PageQueryDTO;
import org.anwang.safe.server.safescan.repository.TransactionEntity;

import java.math.BigInteger;

public class TransactionPageQueryDTO extends PageQueryDTO<TransactionEntity> {

    @ApiModelProperty("区块高度")
    private BigInteger blockNumber;

    @ApiModelProperty("钱包地址")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }




}
