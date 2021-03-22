package me.olook.sdk.addiction.client;

/**
 * 联调环境接口地址
 * @author Red
 */
class RequestTestEndpoint {

    /**
     * 实名认证
     */
    static final String AUTHENTICATION_CHECK = "https://wlc.nppa.gov.cn/test/authentication/check/";

    /**
     * 实名认证结果查询
     */
    static final String AUTHENTICATION_QUERY = "https://wlc.nppa.gov.cn/test/authentication/query/";

    /**
     * 行为上报:登录/登出
     */
    static final String BEHAVIOR_LOGIN_OUT = "https://wlc.nppa.gov.cn/test/collection/loginout/";
}
