package com.base.framework.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Rest api 执行状态描述
 *
 * @param <T>
 * @author tanglz
 */
@JsonInclude(Include.NON_EMPTY)
public class ApiResult<T> {
    public static ApiResult VOID = new ApiResult();

    /**
     * 操作执行情况代码，0-表示成功，其他表示失败
     */
    private int state;
    /**
     * 表示 state 字段的名称，该字段的名称一定要和{@link ApiResult.state}属性名保持一致
     */
    public static final String FNAME_STATE = "state";

    /**
     * 异常描述
     */
    private String errorMessage;

    /**
     * 表示 errorMessage 字段的名称，该字段的名称一定要和{@link ApiResult.errorMessage}属性名保持一致
     */
    public static final String FNAME_ERROR_MESSAGE = "errorMessage";

    /**
     * 返回的结果集对象
     */
    private T value;

    /**
     * 表示 value 字段的名称，该字段的名称一定要和{@link ApiResult.value}属性名保持一致
     */
    public static final String FNAME_VALUE = "value";

    /**
     * @param state        执行结果代码
     * @param errorMessage 异常消息
     */
    public ApiResult(int state, String errorMessage) {
        this(state, errorMessage, null);

    }

    /**
     * @param t 结果对象
     */
    public ApiResult(T t) {
        this(0, null, t);

    }

    /**
     * @param state        执行结果代码
     * @param errorMessage 异常消息
     * @param res          API执行结果
     */
    public ApiResult(int state, String errorMessage, T res) {
        this.state = state;
        this.errorMessage = errorMessage;
        this.value = res;

    }

    /**
     * 创建一个ApiResult对象，一般用于restclient获取请求结果
     */
    public ApiResult() {
        super();
    }

    public int getState() {
        return state;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getValue() {
        return value;
    }

}
