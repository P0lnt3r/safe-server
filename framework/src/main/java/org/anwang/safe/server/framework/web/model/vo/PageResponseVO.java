package org.anwang.safe.server.framework.web.model.vo;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class PageResponseVO<ValueObject extends org.anwang.safe.server.framework.web.model.vo.ValueObject,Entity> {

    @ApiModelProperty( "当前第几页" )
    private Long current;

    @ApiModelProperty( "每页多少条数据" )
    private Long pageSize;

    @ApiModelProperty( "合计多少条数据" )
    private Long total;

    @ApiModelProperty( "一共多少页数据" )
    private Long totalPages;

    @ApiModelProperty( "数据列表" )
    private List<ValueObject> records;

    public PageResponseVO<ValueObject,Entity> from(IPage<Entity> page , Class<ValueObject> voClass ) {
        return from( page , voClass , null );
    }

    public PageResponseVO<ValueObject,Entity> from(IPage<Entity> page , Class<ValueObject> voClass , IConvertHandler<Entity , ValueObject> handler ){

        this.current = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.totalPages = page.getPages();

        try {
            Constructor<ValueObject> constructor = voClass.getConstructor();
            List<ValueObject> records = page.getRecords().stream().map( entity -> {
                ValueObject vo = null;
                try {
                    vo = constructor.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                vo.from( entity );
                if ( handler != null ){
                    return handler.handle( entity , vo );
                }
                return vo;
            } ).collect(Collectors.toList());
            this.records = records;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<ValueObject> getRecords() {
        return records;
    }

    public void setRecords(List<ValueObject> records) {
        this.records = records;
    }
}
