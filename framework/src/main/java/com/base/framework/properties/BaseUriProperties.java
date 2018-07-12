package com.base.framework.properties;

import java.util.Map;

/**
 * 配置系统中依赖的服务的uri地址
 *
 * @author tanglz
 */
public abstract class BaseUriProperties {
    private Map<String, String> uris;

    public Map<String, String> getUris() {
        return uris;
    }

    public void setUris(Map<String, String> uris) {
        this.uris = uris;
    }

    /**
     * 根据uris配置的键值查找服务的uri
     *
     * @param code uris配置的键值
     * @return
     */
    public String getUri(String code) {
        return this.uris.get(code);
    }
}
