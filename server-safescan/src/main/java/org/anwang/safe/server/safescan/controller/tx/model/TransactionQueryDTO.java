package org.anwang.safe.server.safescan.controller.tx.model;

import io.swagger.annotations.ApiModelProperty;
import org.anwang.safe.server.framework.web.model.dto.AbsDataTransferObject;
import org.anwang.safe.server.safescan.repository.TransactionEntity;

public class TransactionQueryDTO extends AbsDataTransferObject<TransactionEntity> {

    @ApiModelProperty("交易哈希值")
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
