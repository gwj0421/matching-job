package kimchisoup.matchingjob.service;

public interface RedisService {
    void setValueWithTTL(String prefix, String key, String value);

    String getValue(String prefix, String key);

    boolean hasKey(String prefix, String key);

    void deleteValue(String prefix, String key);
}
