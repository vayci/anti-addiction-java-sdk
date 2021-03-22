package me.olook.sdk.addiction;

import me.olook.sdk.addiction.constant.ResponseCode;
import me.olook.sdk.addiction.kernel.ClientConfig;
import me.olook.sdk.addiction.kernel.ClientContext;
import me.olook.sdk.addiction.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * 正式环境测试用例
 */
public class ProdTest {

    private AntiAddictionClient antiAddictionClient;

    @Before
    public void setUp() throws Exception {

        ClientContext clientContext = ClientContext.builder
                .aClientContext()
                .appId("")
                .bizId("")
                .secretKey("")
                .build();

        ClientConfig clientConfig = ClientConfig.builder
                .aClientConfig()
                .ignoreSSL(true)
                .connectTimeout(4000)
                .readTimeout(4000)
                .maxRetry(1)
                .build();

        antiAddictionClient = new AntiAddictionClient(clientContext,clientConfig);
    }

    @Test
    public void authenticationCheck() {
        AuthenticationCheckRequest request = new AuthenticationCheckRequest();
        request.setAi("200000000000002001");
        request.setIdNum("110000190201010009");
        request.setName("某二一");
        Response response = antiAddictionClient.authenticationCheck(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.OK.getCode(),response.getErrcode());
    }

    @Test
    public void authenticationQuery() {
        AuthenticationQueryRequest request = new AuthenticationQueryRequest();
        request.setAi("300000000000000001");
        Response response = antiAddictionClient.authenticationQuery(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.OK.getCode(),response.getErrcode());
    }

    @Test
    public void behaviorLoginOut() {

        BehaviorLoginOutItem item = new BehaviorLoginOutItem();
        item.setNo(1);
        item.setSi(UUID.randomUUID().toString().replace("-","").substring(0,32));
        item.setBt(1);
        item.setOt(System.currentTimeMillis()/1000);
        item.setCt(2);
        item.setDi(UUID.randomUUID().toString().replace("-","").substring(0,32));
        BehaviorLoginOutListRequest request = new BehaviorLoginOutListRequest();
        ArrayList<BehaviorLoginOutItem> collections = new ArrayList<>();
        collections.add(item);
        request.setCollections(collections);

        Response response = antiAddictionClient.behaviorLoginOut(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.OK.getCode(),response.getErrcode());
    }
}