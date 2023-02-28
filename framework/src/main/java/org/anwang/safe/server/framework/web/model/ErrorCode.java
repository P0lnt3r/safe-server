package org.anwang.safe.server.framework.web.model;

public enum ErrorCode implements IErrorCode {

    // 成功
    SUCCESS( IErrorCode.SUCCESS_CODE , IErrorCode.SUCCESS_DESC ) ,

    EXCEPTION( "10000" , "系统繁忙,请稍后再试" ),
    AUTH_EXCEPTION( "10403" , "认证错误" ),
    SYS_ERR_404( "10404","404 Not Found" ),
    SYS_ERR_405( "10405" , "Http/Method 错误" ),
    SYS_ERR_406( "10406" , "Header/Content-Type 错误" ),

    VALIDATE_EXCEPTION(                             "1000" , "参数错误" ),
    VALIDATE_DATA_ERROR( "1001","跨链数据方向错误" ),

    NULL( "","" );

    private ErrorCode(String code , String desc ){
        this.code = code;
        this.desc = desc;
    }
    public String code ,desc ;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
