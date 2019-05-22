package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class JSON_Utils {

    public static JSONObject updateJson(JSONObject obj, String keyString, String newValue) throws Exception {
        obj.remove("keyString");
        obj.put("keyString",newValue);

        return obj;
    }
}
