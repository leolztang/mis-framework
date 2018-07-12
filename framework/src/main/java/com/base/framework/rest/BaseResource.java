package com.base.framework.rest;

/**
 * REST接口，resource的父类，提供通用的返回结果封装方法
 */
public class BaseResource {

    /**
     * 封装REST API执行结果
     *
     * @param <T>  结果集res的类型
     * @param code api执行结果代码，不能为空
     * @param msg  结果描述
     * @param res  结果集数据，可以为空
     * @return 结果代码，描述，数据的封装
     */
    public <T> ApiResult<T> makeResult(int code, String msg, T res) {
        return new ApiResult<>(code, msg, res);
    }

    /**
     * 封装REST API执行结果
     *
     * @param code api执行结果代码，不能为空
     * @param msg  结果描述
     * @return 结果代码，描述，没有数据封装
     */
    public <T> ApiResult<T> makeResult(int code, String msg) {
        return new ApiResult<>(code, msg, null);
    }

    /**
     * 封装REST API执行结果，使用枚举类<tt>Status</tt>
     *
     * @param status 结果代码和描述的枚举类型
     * @param res    结果集数据，可以为空
     * @return 结果代码，描述，数据的封装
     */
    public <T> ApiResult<T> makeResult(Status status, T res) {
        return new ApiResult<>(status.getCode(), status.getMsg(), res);
    }

    /**
     * 封装REST API执行结果，使用枚举类<tt>Status</tt>，并附带详细描述
     *
     * @param status 结果代码和描述的枚举类型
     * @param detail 结果描述的详细信息
     * @return 结果代码，描述，数据的封装
     */
    public <T> ApiResult<T> makeResult(Status status, String detail) {
        return new ApiResult<>(status.getCode(), status.getMsg() + (detail == null ? "" : ":" + detail), null);
    }

    /**
     * 封装REST API执行结果，对一个表示成功的结果代码和描述，以及数据的封装
     *
     * @param res 结果数据
     * @return 一个表示成功的结果代码和描述，以及数据的封装
     */
    public <T> ApiResult<T> success(T res) {
        return this.makeResult(BaseStatus.SUCCESS, res);
    }

    /**
     * @return 执行结果代码和异常消息
     */
    public <T> ApiResult<T> success() {
        return this.makeResult(BaseStatus.SUCCESS, (T) null);
    }

    /**
     * 封装REST API执行结果，对一个表示失败的结果代码和描述，以及详细描述的封装
     *
     * @param error  错误结果代码和描述的枚举类型
     * @param detail 详细信息描述
     * @return 一个表示失败的结果代码和描述，以及详细信息描述的封装
     */
    public <T> ApiResult<T> fail(Status error, String detail) {
        return this.makeResult(error, detail);
    }

    /**
     * 封装REST API执行结果，对一个表示失败的结果代码和描述.
     *
     * @param msg 错误信息的描述
     * @return 一个表示失败的结果代码和描述的封装
     */
    public <T> ApiResult<T> fail(String msg) {

        return this.makeResult(BaseStatus.FAIL.getCode(), msg);
    }

}
