package com.base.framework.exception;

import com.base.framework.rest.BaseStatus;

/**
 * @author tanglz
 */
public class RpcFailedException extends RestApiException {
    private static final long serialVersionUID = 3308087691054340832L;

    /**
     * @param info 错误详情消息
     */
    public RpcFailedException(String info) {
        super(BaseStatus.SYSTEM_ERR.getCode(), info);
    }

    public RpcFailedException() {
        super(BaseStatus.SYSTEM_ERR.getCode(), BaseStatus.SYSTEM_ERR.getMsg());
    }

    /**
     * @param code 错误代码
     * @param info 错误消息
     */
    public RpcFailedException(int code, String info) {
        super(code, info);
    }
}