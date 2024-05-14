package haut.zsc.music.common;

import com.alibaba.fastjson.JSONObject;

public class Message <T>{
    JSONObject jsonObject = new JSONObject();

    public Message(String message, int code, boolean success,String type,T data) {
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        jsonObject.put("success", success);
        jsonObject.put("type", type);
        jsonObject.put("data", data);
    }
    public JSONObject getMessage() {
        return jsonObject;
    }
}
