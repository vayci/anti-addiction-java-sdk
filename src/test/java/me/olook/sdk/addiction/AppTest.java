package me.olook.sdk.addiction;

import me.olook.sdk.addiction.model.AuthenticationCheckRequest;
import me.olook.sdk.addiction.model.AuthenticationQueryRequest;
import me.olook.sdk.addiction.model.BehaviorLoginOutItem;
import me.olook.sdk.addiction.request.ClientHelper;
import me.olook.sdk.addiction.security.AES;
import me.olook.sdk.addiction.security.HMAC;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.junit.Test;

import java.net.URL;
import java.util.*;

public class AppTest {

    private static final String APP_ID = "";
    private static final String BIZ_ID = "";
    private static final String SECRET_KEY = "";

    /**
     * {"errcode":0,"errmsg":"OK","data":{"result":{"status":0,"pi":"1fffbjzos82bs9cnyj1dna7d6d29zg4esnh99u"}}}
     *
     * {"errcode":0,"errmsg":"OK","data":{"result":{"status":1,"pi":null}}}
     *
     * {"errcode":0,"errmsg":"OK","data":{"result":{"status":2,"pi":null}}}
     */
    @Test
    public void check() throws Exception {
        Map<String,Object> runtime = new HashMap<>();
        runtime.put("ignoreSSL",true);
        runtime.put("connectTimeout",4000);
        runtime.put("readTimeout",4000);
        runtime.put("maxRetry",1);

        AuthenticationCheckRequest request = new AuthenticationCheckRequest();
        request.setAi("200000000000002001");
        request.setIdNum("110000190201010009");
        request.setName("某二一");

        String encrypt = AES.gcmEncrypt(new Gson().toJson(request), SECRET_KEY);
        String json = "{\"data\":\""+encrypt+"\"}";

        URL url = new URL(RequestTestEndpoint.AUTHENTICATION_CHECK);
        OkHttpClient client = ClientHelper.getOkHttpClient(url.getHost(), url.getPort(), runtime);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.addHeader("Content-Type","application/json;charset=utf-8");
        requestBuilder.addHeader("appId",APP_ID);
        requestBuilder.addHeader("bizId",BIZ_ID);
        String time = System.currentTimeMillis()+"";
        requestBuilder.addHeader("timestamps",time);
        requestBuilder.addHeader("sign",
                HMAC.sign(APP_ID,BIZ_ID,time,SECRET_KEY,new HashMap<>(),json));

        Response response = client.newCall(requestBuilder.url(url)
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json))
                .build()).execute();
        System.out.println(response);
        if(response.isSuccessful()){
            assert response.body() != null;
            JsonElement jsonElement = JsonParser.parseString(response.body().string());
            System.out.println(jsonElement.getAsJsonObject().toString());
        }
    }

    /**
     * {"errcode":0,"errmsg":"OK","data":{"result":{"status":0,"pi":"1fffbjzos82bs9cnyj1dna7d6d29zg4esnh99u"}}}
     *
     * {"errcode":0,"errmsg":"OK","data":{"result":{"status":1,"pi":null}}}
     *
     * {"errcode":0,"errmsg":"OK","data":{"result":{"status":2,"pi":null}}}
     */
    @Test
    public void query() throws Exception {
        Map<String,Object> runtime = new HashMap<>();
        runtime.put("ignoreSSL",true);
        runtime.put("connectTimeout",4000);
        runtime.put("readTimeout",4000);
        runtime.put("maxRetry",1);

        String ai = "300000000000000001";

        AuthenticationQueryRequest request = new AuthenticationQueryRequest();
        request.setAi(ai);

        Map<String,String> map = new HashMap<>();
        map.put("ai",ai);

        URL url = new URL(RequestTestEndpoint.AUTHENTICATION_QUERY);
        OkHttpClient client = ClientHelper.getOkHttpClient(url.getHost(), url.getPort(), runtime);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.addHeader("Content-Type","application/json;charset=utf-8");
        requestBuilder.addHeader("appId",APP_ID);
        requestBuilder.addHeader("bizId",BIZ_ID);
        String time = System.currentTimeMillis()+"";
        requestBuilder.addHeader("timestamps",time);
        requestBuilder.addHeader("sign",
                HMAC.sign(APP_ID,BIZ_ID,time,SECRET_KEY,map,""));

        Response response = client.newCall(requestBuilder.url(url+"?ai="+ai)
                .get()
                .build()).execute();
        System.out.println(response);
        if(response.isSuccessful()){
            assert response.body() != null;
            JsonElement jsonElement = JsonParser.parseString(response.body().string());
            System.out.println(jsonElement.getAsJsonObject().toString());
        }
    }

    /**
     *
     * {"errcode":0,"errmsg":"ok"}
     * {"errcode":0,"errmsg":"OK"}
     */
    @Test
    public void data() throws Exception {
        Map<String,Object> runtime = new HashMap<>();
        runtime.put("ignoreSSL",true);
        runtime.put("connectTimeout",4000);
        runtime.put("readTimeout",4000);
        runtime.put("maxRetry",1);

        long currentTimeMillis = System.currentTimeMillis();

        BehaviorLoginOutItem request = new BehaviorLoginOutItem();
        request.setNo(1);
        request.setSi(UUID.randomUUID().toString().replace("-","").substring(0,32));
        request.setBt(1);
        request.setOt(currentTimeMillis/1000);
        request.setCt(2);
        request.setDi(UUID.randomUUID().toString().replace("-","").substring(0,32));


        List<BehaviorLoginOutItem> list = new ArrayList<>();
        list.add(request);
        Map<String, List<BehaviorLoginOutItem>> co = new HashMap<>();
        co.put("collections",list);

        String encrypt = AES.gcmEncrypt(new Gson().toJson(co), SECRET_KEY);
        String json = "{\"data\":\""+encrypt+"\"}";

        URL url = new URL(RequestTestEndpoint.BEHAVIOR_LOGIN_OUT);
        OkHttpClient client = ClientHelper.getOkHttpClient(url.getHost(), url.getPort(), runtime);
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.addHeader("Content-Type","application/json;charset=utf-8");
        requestBuilder.addHeader("appId",APP_ID);
        requestBuilder.addHeader("bizId",BIZ_ID);
        long timestamps = System.currentTimeMillis();
        requestBuilder.addHeader("timestamps",timestamps+"");
        requestBuilder.addHeader("sign",
                HMAC.sign(APP_ID,BIZ_ID,timestamps+"",SECRET_KEY,new HashMap<>(),json));

        Response response = client.newCall(requestBuilder.url(url)
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json))
                .build()).execute();
        System.out.println(response);
        if(response.isSuccessful()){
            assert response.body() != null;
            JsonElement jsonElement = JsonParser.parseString(response.body().string());
            System.out.println(jsonElement.getAsJsonObject().toString());
        }
    }
}