package kimchisoup.matchingjob.utils;

import java.util.HashSet;
import java.util.Set;

public class CustomStringUtils {
    public CustomStringUtils() {
    }

    public static Set<String> convertLowerSet(String origin) {
        Set<String> result = new HashSet<>();
        for (String each : origin.split(",")) {
            result.add(each.toLowerCase());
        }
        return result;
    }
}
