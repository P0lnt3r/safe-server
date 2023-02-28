package org.anwang.safe.server.framework.web.model;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JSONResponseHelper {

    @Autowired
    I18N I18N;

    public JSONResponse success(Object data ){
        return JSONResponse.Build( IErrorCode.SUCCESS_CODE , I18N.getI18NMessage( IErrorCode.SUCCESS_CODE ) , data );
    }

    public JSONResponse error (IErrorCode errorCode ){
        return error(errorCode , "");
    }

    public JSONResponse error(IErrorCode errorCode , String extras ){
        String message = errorCode.desc();
        try {
            message = I18N.getI18NMessage( errorCode.code() );
        } catch (Exception e){

        }
        if (StrUtil.isNotEmpty(extras)){
            message += ":" + extras;
        }
        return JSONResponse.Build( errorCode.code() , message , "" );
    }

}
