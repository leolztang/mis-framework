package com.base.framework.rest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.web.Swagger2Controller;

@RestControllerAdvice
public class ResponseBodyWrapAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // ignore api-docs provided by swagger2
        if (returnType.getMethod().getDeclaringClass().isAssignableFrom(Swagger2Controller.class)
                || returnType.getMethod().getDeclaringClass().isAssignableFrom(ApiResourceController.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 对于文件下载，返回的messageconverter是ByteArrayHttpMessageConverter,直接返回
        if (selectedConverterType != null
                && ByteArrayHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            return body;
        }

        //work around to ignore Exception processed response
        if (body != null && body instanceof Exception) {
            return body;
        }
        //if the method definition is return void ,then I will assume the response had been written.
        if (selectedContentType.equals(MediaType.APPLICATION_JSON_UTF8) || selectedContentType
                .equals(MediaType.APPLICATION_JSON)) {
            if (body == null || Void.class.equals(returnType.getMethod().getReturnType())) {
                return ApiResult.VOID;
            } else {
                return new ApiResult(body);
            }
        } else {
            return body;
        }
    }
}
