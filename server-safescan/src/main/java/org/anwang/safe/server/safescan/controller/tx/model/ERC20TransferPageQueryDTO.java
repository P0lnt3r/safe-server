package org.anwang.safe.server.safescan.controller.tx.model;

import org.anwang.safe.server.framework.web.model.dto.AbsDataTransferObject;
import org.anwang.safe.server.framework.web.model.dto.PageQueryDTO;
import org.anwang.safe.server.safescan.repository.ERC20TransferEntity;

public class ERC20TransferPageQueryDTO extends PageQueryDTO<ERC20TransferEntity> {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
