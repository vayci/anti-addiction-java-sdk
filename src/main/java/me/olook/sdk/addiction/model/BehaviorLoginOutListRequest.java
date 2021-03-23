package me.olook.sdk.addiction.model;

import java.util.List;

/**
 * @author Red
 * @date 2021-03-22 17:16
 */
public class BehaviorLoginOutListRequest implements IClientRequest{

    private List<BehaviorLoginOutItem> collections;

    public List<BehaviorLoginOutItem> getCollections() {
        return collections;
    }

    public void setCollections(List<BehaviorLoginOutItem> collections) {
        this.collections = collections;
    }
}
