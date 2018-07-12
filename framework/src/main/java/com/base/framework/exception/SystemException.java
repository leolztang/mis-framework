package com.base.framework.exception;

import com.base.framework.rest.BaseStatus;

/**
 * 表示系统错误的异常，例如数据库返回不期望的数据格式，系统无法连接等错误
 *
 * @author tanglz
 */
public class SystemException extends RestApiException {
    private static final long serialVersionUID = -1753673163011556437L;

    /**
     * @param info 错误详情消息
     */
    public SystemException(String info) {
        super(BaseStatus.SYSTEM_ERR.getCode(), info);
    }

    /**
     * @param code 错误代码
     * @param info 错误消息
     */
    public SystemException(int code, String info) {
        super(code, info);
    }
}
