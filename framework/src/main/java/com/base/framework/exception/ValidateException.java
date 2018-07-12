package com.base.framework.exception;

import com.alibaba.fastjson.JSONObject;
import com.base.framework.rest.BaseStatus;
import com.base.framework.rest.Status;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 参数验证错误
 */
public class ValidateException extends RestApiException {

    private static final long serialVersionUID = -4258683109278933701L;

    private final Map<String, String> errors = new HashMap<>();

    /**
     * 默认参数验证异常
     */
    public ValidateException() {
        this(BaseStatus.INVALID_PARAM);
    }

    /**
     * 可以指定错误代码和错误消息的参数验证异常
     *
     * @param code
     * @param msg
     */
    public ValidateException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 可以指定错误代码和错误消息的参数验证异常
     *
     * @param msg
     */
    public ValidateException(String msg) {
        super(BaseStatus.INVALID_PARAM.getCode(), msg);
    }

    /**
     * 可以指定错误代码和错误消息的参数验证异常， 以枚举参数指定
     *
     * @param status
     */
    public ValidateException(Status status) {
        super(status.getCode(), status.getMsg());
    }

    /**
     * 使用默认的错误代码和错误消息的参数验证异常， 但是可以描述具体错误信息
     *
     * @param errors 详细错误信息的键值对
     */
    public ValidateException(Map<String, String> errors) {
        this();

        if (errors != null) {
            this.errors.putAll(errors);
        }
    }

    /**
     * 使用默认的错误代码和错误消息的参数验证异常， 但是可以描述具体错误信息
     *
     * @param ex 验证框架抛出的验证异常
     */
    public ValidateException(ConstraintViolationException ex) {
        this(ex.getConstraintViolations());
    }

    /**
     * 使用默认的错误代码和错误消息的参数验证异常， 但是可以描述具体错误信息
     *
     * @param violations 验证框架抛出的验证异常
     */
    public ValidateException(Set<ConstraintViolation<?>> violations) {
        this();
        this.errors.putAll(toMap(violations));
    }

    /**
     * @return 详细异常信息键值对
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * @param name    详细异常信息键
     * @param message 详细异常信息描述
     * @return
     */
    public ValidateException addError(String name, String message) {
        this.errors.put(name, message);
        return this;
    }

    private Map<String, String> toMap(Set<ConstraintViolation<?>> violations) {
        Map<String, String> errs = new HashMap<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<?> violation : violations) {
                errs.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            this.errors.putAll(errors);
        }

        return errs;
    }

    /**
     * @return 详细异常信息的json序列化字符串
     */
    public String getErrosMsg() {
        return JSONObject.toJSONString(this.errors);
    }

}
