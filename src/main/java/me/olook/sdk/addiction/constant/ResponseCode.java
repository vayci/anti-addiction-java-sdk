package me.olook.sdk.addiction.constant;

public enum ResponseCode {

    OK(0,"请求成功"),
    SYS_ERROR(1001,"系统错误"),

    SYS_REQ_RESOURCE_NOT_EXIST(1002,"接口请求的资源不存在"),
    SYS_REQ_METHOD_ERROR(1003,"接口请求方式错误"),
    SYS_REQ_HEADER_MISS_ERROR(1004,"接口请求核心参数缺失"),
    SYS_REQ_IP_ERROR(1005,"接口请求IP地址非法"),
    SYS_REQ_BUSY_ERROR(1006,"接口请求超出流量限制"),
    SYS_REQ_EXPIRE_ERROR(1007,"接口请求过期"),
    SYS_REQ_PARTNER_ERROR(1008,"接口请求方身份非法"),
    SYS_REQ_PARTNER_AUTH_DISABLE(1009,"接口请求方权限未启用"),
    SYS_REQ_AUTH_ERROR(1010,"接口请求方无该接口权限"),
    SYS_REQ_PARTNER_AUTH_ERROR(1011,"接口请求方身份核验错误"),
    SYS_REQ_PARAM_CHECK_ERROR(1012,"接口请求报文核验失败"),

    BUS_AUTH_IDNUM_ILLEGAL(2001,"身份证号格式校验失败"),
    BUS_AUTH_RESOURCE_LIMIT(2002,"实名认证条目已达上限"),
    BUS_AUTH_CODE_NO_AUTH_RECODE(2003,"无该编码提交的实名认证记录"),
    BUS_AUTH_CODE_ALREADY_IN_USE(2004,"编码已经被占用"),

    BUS_COLL_PARTIAL_ERROR(3001,"行为数据部分上报失败"),
    BUS_COLL_BEHAVIOR_NULL_ERROR(3002,"行为数据为空"),
    BUS_COLL_OVER_LIMIT_COUNT(3003,"行为数据超出条目数量限制"),
    BUS_COLL_NO_INVALID(3004,"行为数据编码错误"),
    BUS_COLL_BEHAVIOR_TIME_ERROR(3005,"行为发生时间错误"),
    BUS_COLL_PLAYER_MODE_INVALID(3006,"用户类型无效"),
    BUS_COLL_BEHAVIOR_MODE_INVALID(3007,"行为类型无效"),
    BUS_COLL_PLAYERID_MISS(3008,"缺失PI（用户唯一标识）值"),
    BUS_COLL_DEVICEID_MISS(3009,"缺失DI（设备标识）值"),
    BUS_COLL_PLAYERID_INVALID(3010,"PI（用户唯一标识）值无效"),

    TEST_SYS_ERROR(4001,"测试系统错误"),
    TEST_TASK_NOT_EXIST(4002,"测试任务不存在"),
    TEST_PARAM_INVALID_ERROR(4003,"测试参数无效"),
    ;


    private Integer code;

    private String desc;

    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
