package me.olook.sdk.addiction.kernel;

import me.olook.sdk.addiction.security.AES;

/**
 * @author Red
 */
public class DefaultClient {

    private String appId;

    private String bizId;

    private String secretKey;



    public String sign(){
        return null;
    }

    public String aesEncrypt(String plainText, String key) throws Exception {
        return AES.gcmEncrypt(plainText, key);
    }
}
