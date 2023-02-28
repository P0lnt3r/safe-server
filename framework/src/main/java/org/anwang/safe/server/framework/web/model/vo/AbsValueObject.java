package org.anwang.safe.server.framework.web.model.vo;

import cn.hutool.core.bean.BeanUtil;

public abstract class AbsValueObject<Entity> implements ValueObject<Entity> {

    @Override
    public <ValueObject> ValueObject from(Entity entity) {
        BeanUtil.copyProperties( entity , this );
        return (ValueObject) this;
    }

    @Override
    public <ValueObject> ValueObject from(Entity entity, IConvertHandler<Entity, ValueObject> handler) {
        ValueObject vo = (ValueObject)this;
        if ( handler != null ){
            handler.handle(entity , vo);
            return vo;
        }else{
            return from( entity );
        }
    }


}
