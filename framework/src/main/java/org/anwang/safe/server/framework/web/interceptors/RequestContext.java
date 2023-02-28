package org.anwang.safe.server.framework.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestContext {

    public static ThreadLocal<RequestContext> ThreadLocal = new ThreadLocal<>();

    /************************************************************/
    // IN :
    /** 构建时间,也即访问的起始时间 */
    private Date startTime;
    /** 请求信息 */
    private HttpServletRequest httpServletRequest;
    private String uri ;
    private String body ;
    private String headers;
    /************************************************************/

    /************************************************************/
    // OUT :
    private Object jsonResponse;
    private Exception e;
    /************************************************************/

    private RequestContext(){}

    private RequestContext(HttpServletRequest httpServletRequest ){
        this();
        this.startTime = new Date();
        this.httpServletRequest = httpServletRequest;
        this.uri = httpServletRequest.getRequestURI();
        Enumeration<String> parameterNames = httpServletRequest.getHeaderNames();
        StringBuilder headerBuilder = new StringBuilder();
        while( parameterNames.hasMoreElements() ){
            headerBuilder.append( httpServletRequest.getHeader( parameterNames.nextElement() ) + "\r\n" );
        }
        this.headers = headerBuilder.toString();
        this.body = httpServletRequest.getParameterMap().entrySet().stream()
                .map( entry -> entry.getKey() + "=" + Stream.of( entry.getValue() ).collect(Collectors.joining(",")) )
                .collect(Collectors.joining("&"));
        ThreadLocal.set(this);
    }

    public static RequestContext Build(HttpServletRequest httpServletRequest ){
        return new RequestContext( httpServletRequest );
    }

    public static java.lang.ThreadLocal<RequestContext> getThreadLocal() {
        return ThreadLocal;
    }

    public static void setThreadLocal(java.lang.ThreadLocal<RequestContext> threadLocal) {
        ThreadLocal = threadLocal;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public Object getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(Object jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
