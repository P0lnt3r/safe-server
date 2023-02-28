package org.anwang.safe.server;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class AutoGenerateIDEntity implements BaseEntity {

    @TableId( type = IdType.AUTO , value = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
