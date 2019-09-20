package com.arizal.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class ResponseUtil {

    public static Object setResponse(Constants.RESPONSE response, Object obj, boolean hasBody) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        if (obj != null && !"".equals(obj.toString())) {
            Gson gson = new GsonBuilder().create();
            String data = gson.toJson(obj);
            if (hasBody) {
                res = gson.fromJson(data, HashMap.class);
            } else {
                res.put("data", obj);
            }
            res.put("isError", response.getCode());
            res.put("message", response.getDesc());
        } else {
            res.put("isError", response.getCode());
            res.put("message", response.getDesc());
        }
        return res;
    }
}
