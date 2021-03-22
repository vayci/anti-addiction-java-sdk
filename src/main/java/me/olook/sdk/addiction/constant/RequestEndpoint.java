package me.olook.sdk.addiction.constant;

/**
 * @author Red
 */
public class RequestEndpoint {

    /**
     * 实名认证
     */
    public static final String AUTHENTICATION_CHECK = "https://api.wlc.nppa.gov.cn/idcard/authentication/check";

    /**
     * 实名认证结果查询
     */
    public static final String AUTHENTICATION_QUERY = "http://api2.wlc.nppa.gov.cn/idcard/authentication/query";

    /**
     * 行为上报:登录/登出
     */
    public static final String BEHAVIOR_LOGIN_OUT = "http://api2.wlc.nppa.gov.cn/behavior/collection/loginout";
}
