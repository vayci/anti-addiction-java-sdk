package me.olook.sdk.addiction.request;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * @author Red
 */
public class RetryInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryInterceptor.class);

    private int maxRetry; //最大重试次数
    private int retryNum = 0; //假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
    private long interval;

    public RetryInterceptor(int maxRetry, long interval) {
        this.maxRetry = maxRetry;
        this.interval = interval;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = doRequest(chain, request);
        int retryNum = 0;
        while ((response == null || !response.isSuccessful()) && retryNum <= maxRetry) {
            LOGGER.info("intercept Request is not successful - {}",retryNum);
            try {
                LOGGER.info("Wait for {}",interval);
                Thread.sleep(interval);
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
            retryNum++;
            // retry the request
            response = doRequest(chain, request);
        }
        return response;
    }


    private Response doRequest(Chain chain, Request request) {
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception ignored) {
        }
        return response;
    }
}
