package utils;


import java.util.HashMap;

public class IterateMap extends HashMap {
    public static HashMap getNextMap(HashMap<String, HashMap> map, String key) {
        HashMap<String, HashMap> hashMap = null;

        for (Entry<String, HashMap> entry : map.entrySet())
            if (entry.getKey().equals(key))
                hashMap = entry.getValue();
        return hashMap;
        }
}
