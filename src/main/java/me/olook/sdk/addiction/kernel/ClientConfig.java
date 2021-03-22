package me.olook.sdk.addiction.kernel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Red
 * @date 2021-03-22 16:21
 */
public class ClientConfig implements IClientConfig{

    private boolean ignoreSSL;

    private long connectTimeout;

    private long readTimeout;

    private int maxRetry;

    public boolean isIgnoreSSL() {
        return ignoreSSL;
    }

    public void setIgnoreSSL(boolean ignoreSSL) {
        this.ignoreSSL = ignoreSSL;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    public static final class builder {
        private boolean ignoreSSL;
        private long connectTimeout;
        private long readTimeout;
        private int maxRetry;

        private builder() {
        }

        public static builder aClientConfig() {
            return new builder();
        }

        public builder ignoreSSL(boolean ignoreSSL) {
            this.ignoreSSL = ignoreSSL;
            return this;
        }

        public builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public builder maxRetry(int maxRetry) {
            this.maxRetry = maxRetry;
            return this;
        }

        public ClientConfig build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setIgnoreSSL(ignoreSSL);
            clientConfig.setConnectTimeout(connectTimeout);
            clientConfig.setReadTimeout(readTimeout);
            clientConfig.setMaxRetry(maxRetry);
            return clientConfig;
        }
    }

    @Override
    public Map<String, Object> toConfigMap() {
        Map<String,Object> configMap = new HashMap<>();
        configMap.put("ignoreSSL",ignoreSSL);
        configMap.put("connectTimeout",connectTimeout);
        configMap.put("readTimeout",readTimeout);
        configMap.put("maxRetry",maxRetry);
        return configMap;
    }
}
