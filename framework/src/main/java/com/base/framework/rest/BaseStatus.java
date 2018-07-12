package com.base.framework.rest;

/**
 * Rest api 执行状态定义
 */
public enum BaseStatus implements Status {

    SUCCESS(0, null),
    FAIL(1, "操作失败"),
    SYSTEM_ERR(1, "系统异常，请联系管理员"),
    INVALID_PARAM(1, "参数不合法"),
    INVALID_URI(1, "请求的资源不存在");

    /**
     * 错误代码
     */
    private final int code;

    /**
     * 错误描述
     */
    private final String msg;

    private BaseStatus(int val, String info) {
        this.code = val;
        this.msg = info;

    }

    /* (non-Javadoc)
     * @see com.huaweisoft.commons.framework.rest.Status#getCode()
     */
    @Override
    public int getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see com.huaweisoft.commons.framework.rest.Status#getMsg()
     */
    @Override
    public String getMsg() {
        return msg;
    }
}
