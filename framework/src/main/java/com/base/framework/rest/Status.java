package com.base.framework.rest;

/**
 * 为API执行结果的枚举类提供方法接口
 *
 * @author tanglz
 */
public interface Status {
    /**
     * @return 错误代码
     */
    public int getCode();

    /**
     * @return 错误描述
     */
    public String getMsg();
}
