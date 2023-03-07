package org.anwang.safe.server.safescan.controller.utils.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.anwang.safe.server.framework.web.model.vo.AbsValueObject;
import org.anwang.safe.server.safescan.repository.AbiMethodSignatureEntity;

@ApiModel("Abi函数签名编码")
public class AbiMethodSignatureVO extends AbsValueObject<AbiMethodSignatureEntity> {

    @ApiModelProperty("函数名称")
    private String method;

    @ApiModelProperty("函数签名")
    private String signature;

    @ApiModelProperty("16进制ABI编码")
    private String hex;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
