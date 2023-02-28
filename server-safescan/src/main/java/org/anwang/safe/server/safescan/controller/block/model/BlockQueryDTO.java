package org.anwang.safe.server.safescan.controller.block.model;

import org.anwang.safe.server.framework.web.model.dto.AbsDataTransferObject;
import org.anwang.safe.server.safescan.repository.BlockEntity;

import java.math.BigInteger;

public class BlockQueryDTO extends AbsDataTransferObject<BlockEntity> {

    private BigInteger number;

    private String hash;

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
