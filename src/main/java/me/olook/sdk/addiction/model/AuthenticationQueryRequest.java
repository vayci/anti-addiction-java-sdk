package me.olook.sdk.addiction.model;

import java.util.Map;

/**
 * @author Red
 */
public class AuthenticationQueryRequest implements IClientRequest{

    private String ai;

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    @Override
    public Map<String, String> toParamMap() {
        return null;
    }
}
