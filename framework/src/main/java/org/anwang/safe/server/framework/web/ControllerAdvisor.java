package org.anwang.safe.server.framework.web;

import org.anwang.safe.server.framework.web.interceptors.LogTraceBuildInterceptor;
import org.anwang.safe.server.framework.web.interceptors.RequestContext;
import org.anwang.safe.server.framework.web.model.ErrorCode;
import org.anwang.safe.server.framework.web.model.IErrorCode;
import org.anwang.safe.server.framework.web.model.JSONResponse;
import org.anwang.safe.server.framework.web.model.JSONResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

@RestControllerAdvice
public class ControllerAdvisor extends RequestBodyAdviceAdapter implements ResponseBodyAdvice {

    @Autowired
    JSONResponseHelper jsonResponseHelper;

    /**
     * ResponseBodyAdvice 只要是 RestController,都 true
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getDeclaringClass().getDeclaredAnnotation(RestController.class) != null ;
    }

    /**
     * RequestBodyAdviceAdapter 针对 @RequestBody 注解生效,直接返回 true
     * @param methodParameter
     * @param targetType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        byte[] bytes = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(bytes);
        LogTraceBuildInterceptor.log.info( "参数:{} " , new String(bytes) );
        HttpInputMessage _inputMessage = new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(bytes);
            }
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
        return super.beforeBodyRead(_inputMessage, parameter, targetType, converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        boolean isGet = returnType.getMethod().getDeclaredAnnotation(GetMapping.class) != null;
        if (isGet){
            RequestContext requestContext = RequestContext.ThreadLocal.get();
            if ( requestContext != null ){
                requestContext.setJsonResponse(body);
            }
            return body;
        }
        return jsonResponseHelper.success(body);
    }

    @ExceptionHandler(BindException.class)
    public JSONResponse handleBindException(BindException bindExcetion){
        IErrorCode errorCode = ErrorCode.VALIDATE_EXCEPTION;
        FieldError fieldError = bindExcetion.getFieldError();
        bindExcetion.printStackTrace();
        return jsonResponseHelper.error( errorCode , fieldError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public JSONResponse handleException( Exception exception ){
        exception.printStackTrace();
        IErrorCode errorCode = ErrorCode.EXCEPTION;
        return jsonResponseHelper.error(errorCode);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Object handle405Exception(Exception e , HttpServletRequest request){
        return jsonResponseHelper.error(ErrorCode.SYS_ERR_405);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Object handleContentTypeException(Exception e , HttpServletRequest request){
        return jsonResponseHelper.error(ErrorCode.SYS_ERR_406);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public Object handle404Exception2(){
        return jsonResponseHelper.error(ErrorCode.SYS_ERR_404);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object handle404Exception(){
        return jsonResponseHelper.error(ErrorCode.SYS_ERR_404);
    }


}
