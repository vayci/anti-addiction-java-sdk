package me.olook.sdk.addiction.model;

import java.util.Map;

public interface IClientRequest {

    /**
     * GET请求参数必须实现此方法
     */
    public Map<String,String> toParamMap();

}
