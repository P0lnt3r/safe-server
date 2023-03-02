package org.anwang.safe.server.safescan.controller.address.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import org.anwang.safe.server.framework.web.model.vo.AbsValueObject;
import org.anwang.safe.server.safescan.repository.AddressPropEntity;

public class AddressPropVO extends AbsValueObject<AddressPropEntity> {

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("子类型")
    private String subType;

    @ApiModelProperty("地址标签")
    private String tag;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("属性信息,以JSON表示")
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
