package me.olook.sdk.addiction;

import me.olook.sdk.addiction.model.AuthenticationCheckRequest;
import me.olook.sdk.addiction.model.AuthenticationQueryRequest;
import me.olook.sdk.addiction.model.BehaviorLoginOutListRequest;
import me.olook.sdk.addiction.model.Response;

public interface IClient {

    /**
     * 实名认证
     */
    Response authenticationCheck(AuthenticationCheckRequest request);

    /**
     * 实名认证
     */
    Response authenticationQuery(AuthenticationQueryRequest request);

    /**
     * 用户登录登出行为上报
     */
    Response behaviorLoginOut(BehaviorLoginOutListRequest request);
}
