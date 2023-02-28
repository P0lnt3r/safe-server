package org.anwang.safe.server.framework.web.model.vo;

import java.io.Serializable;

public interface ValueObject<Entity> extends Serializable {

    <ValueObject> ValueObject from( Entity entity );

    <ValueObject> ValueObject from( Entity entity ,IConvertHandler<Entity , ValueObject> handler );

}
