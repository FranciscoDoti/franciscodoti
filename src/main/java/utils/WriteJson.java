package utils;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJson {

    public static void setJson(JSONObject json, File jsonFile) {
        try (FileWriter file = new FileWriter(jsonFile.getAbsolutePath())) {

            file.write(json.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}