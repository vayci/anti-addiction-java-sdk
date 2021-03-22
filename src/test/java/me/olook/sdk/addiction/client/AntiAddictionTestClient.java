package me.olook.sdk.addiction.client;

import me.olook.sdk.addiction.AntiAddictionClient;
import me.olook.sdk.addiction.kernel.ClientContext;
import me.olook.sdk.addiction.kernel.IClientConfig;
import me.olook.sdk.addiction.model.AuthenticationCheckRequest;
import me.olook.sdk.addiction.model.AuthenticationQueryRequest;
import me.olook.sdk.addiction.model.BehaviorLoginOutListRequest;
import me.olook.sdk.addiction.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 联调环境client
 * @author Red
 */
public class AntiAddictionTestClient extends AntiAddictionClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AntiAddictionTestClient.class);

    public AntiAddictionTestClient(ClientContext clientContext, IClientConfig clientConfig) {
        super(clientContext, clientConfig);
    }

    public Response authenticationCheck(AuthenticationCheckRequest request, String testCode) {
        try {
            return post(RequestTestEndpoint.AUTHENTICATION_CHECK + testCode, request);
        } catch (Exception e) {
            LOGGER.error("error invoke authenticationCheck, exception: {}",e.getMessage());
            return null;
        }
    }

    public Response authenticationQuery(AuthenticationQueryRequest request, String testCode) {
        try {
            return get(RequestTestEndpoint.AUTHENTICATION_QUERY + testCode, request);
        } catch (Exception e) {
            LOGGER.error("error invoke authenticationQuery, exception: {}",e.getMessage());
            return null;
        }
    }

    public Response behaviorLoginOut(BehaviorLoginOutListRequest request, String testCode) {
        try {
            return post(RequestTestEndpoint.BEHAVIOR_LOGIN_OUT + testCode, request);
        } catch (Exception e) {
            LOGGER.error("error invoke behaviorLoginOut, exception: {}",e.getMessage());
            return null;
        }
    }
}
