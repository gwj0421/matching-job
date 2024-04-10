package kimchisoup.matchingjob.service;

public interface RedisService {
    void setValueWithTTL(String key, String value);

    String getValue(String key);

    boolean hasKey(String key);

    void deleteValue(String key);
}
