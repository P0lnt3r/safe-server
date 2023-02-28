package org.anwang.safe.server.framework.web.model.dto;

/**
 * DTO 自定义转换 Entity 实现
 * @param <DataTransferObject>
 * @param <Entity>
 */
public interface IConvertHandler<Entity> {

    Entity handle( DataTransferObject dto , Entity entity);

}
