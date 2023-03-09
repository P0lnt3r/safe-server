package org.anwang.safe.server.safescan.controller.utils.model;

import org.anwang.safe.server.framework.web.model.vo.AbsValueObject;
import org.anwang.safe.server.safescan.repository.AddressAbiEntity;

public class AddressAbiVO extends AbsValueObject<AddressAbiEntity> {

    private String address;

    private String abi;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbi() {
        return abi;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }
}
