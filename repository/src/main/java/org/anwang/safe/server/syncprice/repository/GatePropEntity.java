package org.anwang.safe.server.syncprice.repository;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.anwang.safe.server.AutoGenerateIDEntity;

@TableName("gate_prop")
public class GatePropEntity extends AutoGenerateIDEntity {

    @TableField("prop_key")
    private String propKey;

    @TableField("prop_val")
    private String propVal;

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    public String getPropVal() {
        return propVal;
    }

    public void setPropVal(String propVal) {
        this.propVal = propVal;
    }

}
