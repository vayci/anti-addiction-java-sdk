package me.olook.sdk.addiction.kernel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.olook.sdk.addiction.model.IClientRequest;
import me.olook.sdk.addiction.model.Response;
import me.olook.sdk.addiction.request.ClientHelper;
import me.olook.sdk.addiction.security.AES;
import me.olook.sdk.addiction.security.HMAC;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Collections;
import java.util.Map;

/**
 * @author Red
 */
public class DefaultClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultClient.class);

    private ClientContext clientContext;

    private Map<String,Object> clientConfigMap;

    public DefaultClient(ClientContext clientContext, IClientConfig clientConfig) {
        this.clientContext = clientContext;
        this.clientConfigMap = clientConfig.toConfigMap();
    }

    protected String encryptBody(IClientRequest clientRequest){
        String encrypt = AES.gcmEncrypt(new Gson().toJson(clientRequest), clientContext.getSecretKey());
        return  "{\"data\":\""+encrypt+"\"}";
    }

    protected Response get(String uri, IClientRequest request) throws Exception {

        URL url = new URL(uri);
        OkHttpClient client = ClientHelper.getOkHttpClient(url.getHost(), url.getPort(), clientConfigMap);

        Request.Builder requestBuilder = generateRequestWithHeader("GET", request, "");

        okhttp3.Response response = client.newCall(requestBuilder.url(url+"?ai=")
                .get()
                .build()).execute();

        if(response.isSuccessful()){
            assert response.body() != null;
            JsonElement jsonElement = JsonParser.parseString(response.body().string());
            return new Gson().fromJson(jsonElement, Response.class);
        }else{
            LOGGER.error("error request to {} , response code: {}, message: {}",uri,response.code(),response.message());
            return null;
        }
    }

    protected Response post(String uri, IClientRequest request) throws Exception {

        URL url = new URL(uri);
        OkHttpClient client = ClientHelper.getOkHttpClient(url.getHost(), url.getPort(), clientConfigMap);

        String encryptBody = encryptBody(request);

        Request.Builder requestBuilder = generateRequestWithHeader("POST", request, encryptBody);

        okhttp3.Response response = client.newCall(requestBuilder.url(url+"?ai=")
                .post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"),encryptBody))
                .build()).execute();

        if(response.isSuccessful()){
            assert response.body() != null;
            JsonElement jsonElement = JsonParser.parseString(response.body().string());
            return new Gson().fromJson(jsonElement, Response.class);
        }else{
            LOGGER.error("error request to {} , response code: {}, message: {}",uri,response.code(),response.message());
            return null;
        }
    }

    protected Request.Builder generateRequestWithHeader(String httpMethod, IClientRequest request, String encryptBody){

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.addHeader("Content-Type","application/json;charset=utf-8");
        requestBuilder.addHeader("appId",clientContext.getAppId());
        requestBuilder.addHeader("bizId",clientContext.getBizId());
        String time = String.valueOf(System.currentTimeMillis());
        requestBuilder.addHeader("timestamps",time);

        if("POST".equals(httpMethod)){
            requestBuilder.addHeader("sign",
                    HMAC.sign(clientContext.getAppId(),clientContext.getBizId(),
                            time,clientContext.getSecretKey(),
                            Collections.emptyMap(),encryptBody));
        }
        if("GET".equals(httpMethod)){
            requestBuilder.addHeader("sign",
                    HMAC.sign(clientContext.getAppId(),clientContext.getBizId(),
                            time,clientContext.getSecretKey(),
                            request.toParamMap(),""));
        }
        return requestBuilder;
    }
}
