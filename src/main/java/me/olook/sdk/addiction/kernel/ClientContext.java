package me.olook.sdk.addiction.kernel;

/**
 * @author Red
 */
public class ClientContext {

    private String appId;

    private String bizId;

    private String secretKey;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public static final class builder {
        private String appId;
        private String bizId;
        private String secretKey;

        private builder() {
        }

        public static builder aClientContext() {
            return new builder();
        }

        public builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public builder bizId(String bizId) {
            this.bizId = bizId;
            return this;
        }

        public builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public ClientContext build() {
            ClientContext clientContext = new ClientContext();
            clientContext.setAppId(appId);
            clientContext.setBizId(bizId);
            clientContext.setSecretKey(secretKey);
            return clientContext;
        }
    }
}
