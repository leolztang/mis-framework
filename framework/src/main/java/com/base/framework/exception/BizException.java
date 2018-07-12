package com.base.framework.exception;

import com.base.framework.rest.BaseStatus;

/**
 * 业务逻辑异常
 */
public class BizException extends RestApiException {

    private static final long serialVersionUID = -1753673163011556437L;

    public BizException(String info) {
        super(BaseStatus.FAIL.getCode(), info);
    }

    public BizException(int code, String info) {
        super(code, info);
    }

}
