package org.anwang.safe.server.framework.web.model.dto;


import cn.hutool.core.bean.BeanUtil;
import org.anwang.safe.server.framework.utils.ReflectUtil;


public abstract class AbsDataTransferObject<Entity> implements DataTransferObject<Entity> {

    @Override
    public Entity convert() {
        Entity entity = null;
        try {
            entity = (Entity) ReflectUtil.getGenericsClass( this.getClass() , 0 ).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtil.copyProperties( this , entity );
        return entity;
    }

    @Override
    public <DataTransferObject> Entity convert(Class<DataTransferObject> clazz, IConvertHandler<Entity> handler) {
        if ( handler != null ){
            try {
                Entity entity = (Entity) ReflectUtil.getGenericsClass( this.getClass() , 0 ).newInstance();
                return handler.handle( this, entity );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convert();
    }
}
