package map;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        System.out.println(map);
        for(String key: map.keySet()){
            if(key.equals("2")){
                map.remove(key);
            }
        }
        System.out.println(map);
    }
}
