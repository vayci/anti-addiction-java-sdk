package me.olook.sdk.addiction;

import me.olook.sdk.addiction.constant.RequestEndpoint;
import me.olook.sdk.addiction.kernel.ClientContext;
import me.olook.sdk.addiction.kernel.DefaultClient;
import me.olook.sdk.addiction.kernel.IClientConfig;
import me.olook.sdk.addiction.model.AuthenticationCheckRequest;
import me.olook.sdk.addiction.model.AuthenticationQueryRequest;
import me.olook.sdk.addiction.model.BehaviorLoginOutListRequest;
import me.olook.sdk.addiction.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Red
 */
public class AntiAddictionClient extends DefaultClient implements IClient{

    private static final Logger LOGGER = LoggerFactory.getLogger(AntiAddictionClient.class);

    public AntiAddictionClient(ClientContext clientContext, IClientConfig clientConfig) {
        super(clientContext, clientConfig);
    }

    @Override
    public Response authenticationCheck(AuthenticationCheckRequest request) {
        try {
            return post(RequestEndpoint.AUTHENTICATION_CHECK, request);
        } catch (Exception e) {
            LOGGER.error("error invoke authenticationCheck, exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Response authenticationQuery(AuthenticationQueryRequest request) {
        try {
            return get(RequestEndpoint.AUTHENTICATION_QUERY, request);
        } catch (Exception e) {
            LOGGER.error("error invoke authenticationQuery, exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Response behaviorLoginOut(BehaviorLoginOutListRequest request) {
        try {
            return post(RequestEndpoint.BEHAVIOR_LOGIN_OUT, request);
        } catch (Exception e) {
            LOGGER.error("error invoke behaviorLoginOut, exception: {}",e.getMessage());
            return null;
        }
    }

}
