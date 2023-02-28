package org.anwang.safe.server.framework.web.model.vo;

public interface IConvertHandler< Entity , ValueObject > {

    ValueObject handle( Entity entity , ValueObject vo );

}
