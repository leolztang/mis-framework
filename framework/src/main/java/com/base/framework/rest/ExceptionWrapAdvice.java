package com.base.framework.rest;

import com.base.framework.exception.BizException;
import com.base.framework.exception.SystemException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.base.framework.exception.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

/**
 * 对Resource接口的结果同一处理
 */
@ControllerAdvice(annotations = {RestController.class})
public class ExceptionWrapAdvice extends BaseResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateException.class);

    private static final String VALIDATION_MSG_TEMPLATE = "参数验证失败：{}";

    /*
     *开发注意：@ExceptionHandler标记的方法的参数要与ExceptionHandler的value属性一致或者为value指定类的父类，否则无法触发异常处理
     *例如以下方法就不能触发异常处理，但是不会有任何编译和运行时错误
     * @ExceptionHandler(value = ValidateException.class)
     * public ApiResult<Map<String, String>> test(BizException ex) throws Exception {
     *
     **/

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResult<Map<String, String>> handleNoResourceException(NoHandlerFoundException ex) throws Exception {
        String msg = "请求的资源不存在，uri=" + ex.getRequestURL() + ",method=" + ex.getHttpMethod();
        LOGGER.error(msg);

        return makeResult(BaseStatus.INVALID_URI.getCode(), msg);
    }

    /**
     * 统一处理Resource抛出的数据验证异常，Resource层在做数据校验时抛出的ValidateException将由此方法处理
     *
     * @param ex 数据校验错误
     * @return 对数据校验错误代码和信息的处理结果
     */
    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult<Map<String, String>> handleValidateException(ValidateException ex) {

        LOGGER.error(VALIDATION_MSG_TEMPLATE, ex.getErrosMsg() + "-----" + ex.getMessage());

        return makeResult(ex.getCode(), ex.getMessage(), ex.getErrors());
    }

    /**
     * @param ex 参数绑定错误
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult<Map<String, String>> handleBindExceptionException(BindException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return extractMsg(ex.getBindingResult());
    }

    /**
     * @param ex 方法参数异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult<Map<String, String>> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return extractMsg(ex.getBindingResult());
    }

    /**
     * @param ex 参数校验异常
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult<Map<String, String>> handleValidateException(ConstraintViolationException ex) {
        ValidateException vex = new ValidateException(ex);
        LOGGER.error(vex.getErrosMsg(), ex);
        return makeResult(vex.getCode(), vex.getMessage(), vex.getErrors());
    }

    /**
     * 处理参数有误,json不能正确转换为对象异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult<Object> handleHttpMessageConversionException(HttpMessageConversionException ex) {
        LOGGER.error(ex.getMessage(), ex);
        String msg;
        Throwable throwable = ex.getCause();
        if (throwable instanceof InvalidFormatException) {
            InvalidFormatException ex2 = (InvalidFormatException) throwable;
            msg = String.format("参数类型不匹配，参数值：%s,期望格式：%s", ex2.getValue(), ex2.getTargetType().getName());
        } else if (throwable instanceof JsonParseException) {
            JsonParseException ex2 = (JsonParseException) throwable;
            msg = String.format("参数有误,json数据格式有误:%s", ex2.getOriginalMessage());
        } else if (throwable instanceof JsonMappingException) {
            msg = String.format("参数有误,参数格式错误,转换失败 ");
        } else {
            msg = String.format("参数有误  %s", ex.getMessage());
        }
        return makeResult(BaseStatus.INVALID_PARAM.getCode(), msg);
    }

    /**
     * 处理参数类型转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class, ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult<Object> handleTypeMismatchException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);

        String msg;
        if (ex instanceof TypeMismatchException) {
            TypeMismatchException ex2 = (TypeMismatchException) ex;
            msg = String.format("类型不匹配{%s},required：{%s}", ex2.getValue(), ex2.getRequiredType().getName());
        } else {
            msg = ex.getMessage();
        }
        return makeResult(BaseStatus.INVALID_PARAM, msg);
    }

    /**
     * @param ex 内部业务异常
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult<Object> handleBizException(BizException ex) {
        // 业务异常不记录日志
        if (LOGGER.isDebugEnabled()) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return makeResult(ex.getCode(), ex.getMessage());
    }

    /**
     * @param ex 内部业务异常
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult<Object> handleSystemException(SystemException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return makeResult(ex.getCode(), ex.getMessage());
    }

    /**
     * @param ex 403异常
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResult<Object> handleForbiddenException(AccessDeniedException ex) {
        // 鉴权失败，不记录日志
        if (LOGGER.isDebugEnabled()) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return fail(ex.getMessage());
    }

    /**
     * 处理其他所有未处理的异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult<Object> handleAllException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return fail(BaseStatus.SYSTEM_ERR, null);
    }

    private ApiResult<Map<String, String>> extractMsg(BindingResult bindingResult) {
        ValidateException vex = new ValidateException();
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError err : errors) {
                vex.addError(err.getField(), err.getDefaultMessage());
            }
        }
        return makeResult(vex.getCode(), vex.getMessage(), vex.getErrors());
    }
}
