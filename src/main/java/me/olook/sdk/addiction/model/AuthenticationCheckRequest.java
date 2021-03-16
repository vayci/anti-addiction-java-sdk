package me.olook.sdk.addiction.model;

/**
 * @author Red
 */
public class AuthenticationCheckRequest {

    private String ai;

    private String name;

    private String idNum;

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }


    public static final class builder {
        private String ai;
        private String name;
        private String idNum;

        private builder() {
        }

        public static builder anAuthenticationCheckRequest() {
            return new builder();
        }

        public builder ai(String ai) {
            this.ai = ai;
            return this;
        }

        public builder name(String name) {
            this.name = name;
            return this;
        }

        public builder idNum(String idNum) {
            this.idNum = idNum;
            return this;
        }

        public AuthenticationCheckRequest build() {
            AuthenticationCheckRequest authenticationCheckRequest = new AuthenticationCheckRequest();
            authenticationCheckRequest.setAi(ai);
            authenticationCheckRequest.setName(name);
            authenticationCheckRequest.setIdNum(idNum);
            return authenticationCheckRequest;
        }
    }
}
