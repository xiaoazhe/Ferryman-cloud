package com.ferry.admin.websocket.response;


import java.util.HashMap;
import java.util.Map;

/**
 * 描述：通用返回对象
 */
public class CommonResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 6199093063476482538L;

    public CommonResponse() {
        put("code", "200");
    }

    public CommonResponse(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public CommonResponse(int code, String msg, Object data) {
        put("code", code);
        put("msg", msg);
        put("data", data);
    }

    public static CommonResponse error() {
        return new CommonResponse(0, "失败");
    }

    public static CommonResponse error(String msg) {
        return error(0, msg);
    }

    public static CommonResponse error(int code, String msg) {
        CommonResponse r = new CommonResponse();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static CommonResponse ok(String msg) {
        CommonResponse r = new CommonResponse();
        r.put("msg", msg);
        return r;
    }

    public static CommonResponse ok(String code, String msg) {
        CommonResponse r = new CommonResponse();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static CommonResponse ok(Map<String, Object> map) {
        CommonResponse r = new CommonResponse();
        r.putAll(map);
        return r;
    }

    public static CommonResponse ok() {
        return new CommonResponse(200, "K");
    }

    public static CommonResponse ok(Object data) {
        return new CommonResponse(200, "OK", data);
    }

    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
