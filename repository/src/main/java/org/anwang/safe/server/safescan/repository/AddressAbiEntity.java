package org.anwang.safe.server.safescan.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.anwang.safe.server.AutoGenerateIDEntity;

@TableName("address_abi")
public class AddressAbiEntity extends AutoGenerateIDEntity {

    @TableField("address")
    private String address;

    @TableField("abi")
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
