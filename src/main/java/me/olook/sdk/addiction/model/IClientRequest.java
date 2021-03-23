package me.olook.sdk.addiction.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface IClientRequest {

    /**
     * GET参数转换为map，
     */
    default Map<String,String> toParamMap(){
        Class c = this.getClass();
        Map<String, String> map = new HashMap<String, String>();
        try {
            Field[] fs = c.getDeclaredFields();
            for (Field f : fs) {
                f.setAccessible(true);
                String st = f.get(this)+"";
                String str = st.replaceAll(" ", "");
                if (str.equals("")||str.equals("null")) {
                    map.put(f.getName(), "");
                    continue;
                }
                map.put(f.getName(), st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

}
