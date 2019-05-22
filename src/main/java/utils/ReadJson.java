package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    private static JSONObject jsonObject = new JSONObject();
    private static Object obj = new Object();

    public static JSONArray getJson(FileReader jsonFile) {

        JSONParser parser = new JSONParser();
        JSONArray ja = new JSONArray();

        try {
            obj = parser.parse(jsonFile);
            if (obj instanceof JSONObject) {
                JSONObject jo = (JSONObject) obj;

            }

            if (obj instanceof JSONArray) {
                ja = (JSONArray) obj;

            }


        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( ParseException e ) {
            e.printStackTrace();
        }

        return ja;
    }
}
