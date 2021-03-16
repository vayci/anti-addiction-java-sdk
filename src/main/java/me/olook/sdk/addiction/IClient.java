package me.olook.sdk.addiction;

import me.olook.sdk.addiction.model.BehaviorLoginOutRequest;
import me.olook.sdk.addiction.model.Response;

import java.util.List;

public interface IClient {

    /**
     * 实名认证
     * @param ai 游戏内部成员标识
     * @param name 用户姓名
     * @param idNum 用户身份证号码
     */
    Response authenticationCheck(String ai, String name, String idNum);

    /**
     * 实名认证
     * @param ai 游戏内部成员标识
     */
    Response authenticationQuery(String ai);

    /**
     * 用户登录登出行为上报
     * @param collections 行为集合
     */
    Response behaviorLoginOut(List<BehaviorLoginOutRequest> collections);
}
