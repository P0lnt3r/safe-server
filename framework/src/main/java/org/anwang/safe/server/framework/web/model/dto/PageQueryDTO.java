package org.anwang.safe.server.framework.web.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import org.anwang.safe.server.framework.utils.ReflectUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * 结合 mybatis-plus 分页查询IPage的上层封装
 * @param <Entity>
 */
public abstract class PageQueryDTO< Entity > extends AbsDataTransferObject< Entity > {

    @ApiModelProperty( "查询第几页数据" )
    @NotNull
    @Min( value = 1 )
    private Long current;

    @NotNull
    @Min( value = 1 )
    @ApiModelProperty( "每页多少条数据" )
    private Long pageSize;

    @ApiModelProperty( "排序属性" )
    private String orderProp;

    @ApiModelProperty( "排序方式[asc:正序,desc:倒序]" )
    private String orderMode;

    /**
     * 子类可以重写这个方法来定义 prop -> column 的值,从而构建 IPage 对象
     * @param orderProp
     * @return
     */
    public String getColumn( String orderProp ){
        return null;
    }

    public Page<Entity> convertPage() {
        Page<Entity> page = new Page<>();
        page.setCurrent( this.current );
        page.setSize(    this.pageSize );
        if ( orderProp != null && !"".equals(orderProp.trim()) ){
            String column = getColumn( this.orderProp );
            if ( column == null ){
                try {
                    Class clazz = ReflectUtil.getGenericsClass( this.getClass() , 0 );
                    Field field = clazz.getDeclaredField( orderProp );
                    TableField tableField = null;
                    if ( (tableField = field.getDeclaredAnnotation( TableField.class )) != null ){
                        String columnName = tableField.value(); // 属性对应的列名
                        if ( !"asc".equals( orderMode ) && !"desc".equals(orderMode) ){
                            orderMode = "desc";
                        }
                        OrderItem orderItem = new OrderItem();
                        orderItem.setAsc( "asc".equals( orderMode ) );
                        orderItem.setColumn(columnName);
                        page.addOrder(orderItem);
                    }
                }catch ( Exception e ){
                    column = null;
                }
            }
        }
        return page;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderProp() {
        return orderProp;
    }

    public void setOrderProp(String orderProp) {
        this.orderProp = orderProp;
    }

    public String getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }
}
