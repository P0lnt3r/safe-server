package org.anwang.safe.server.framework.web.model;


import org.anwang.safe.server.framework.web.interceptors.RequestContext;

public class JSONResponse<T> {

    private String code;

    private String message;

    private T data;

    private JSONResponse(){
        code = "";
        message = "";
        RequestContext requestContext = RequestContext.ThreadLocal.get();
        if ( requestContext != null ){
            requestContext.setJsonResponse(this);
        }
    }

    public static JSONResponse Build(String code , String message, Object data ){
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setCode(code);
        jsonResponse.setMessage(message);
        jsonResponse.setData(data);
        return jsonResponse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
