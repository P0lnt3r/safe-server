package org.anwang.safe.server.safescan.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.anwang.safe.server.AutoGenerateIDEntity;

@TableName("address_prop")
public class AddressPropEntity extends AutoGenerateIDEntity {

    @TableField("address")
    private String address;

    @TableField("type")
    private String type;

    @TableField("sub_type")
    private String subType;

    @TableField("tag")
    private String tag;

    @TableField("remark")
    private String remark;

    @TableField("prop")
    private String prop;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}
