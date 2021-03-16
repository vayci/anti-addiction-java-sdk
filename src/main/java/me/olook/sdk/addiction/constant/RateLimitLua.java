package me.olook.sdk.addiction.constant;

/**
 * @author Red
 */
public class RateLimitLua {

    public static final String SCRIPT = "local c\n" +
            "c = redis.call('get',KEYS[1])\n" +
            "if c and tonumber(c) > tonumber(ARGV[1]) then\n" +
            "return c;\n" +
            "end\n" +
            "c = redis.call('incr',KEYS[1])\n" +
            "if tonumber(c) == 1 then\n" +
            "redis.call('expire',KEYS[1],ARGV[2])\n" +
            "end\n" +
            "return c;";
}
