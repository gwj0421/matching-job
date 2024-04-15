package kimchisoup.matchingjob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    public static final String SMS_PREFIX = "sms:";
    public static final String EMAIL_PREFIX = "email:";

    private static final int LIMIT_TIME = 5 * 60;

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void setValueWithTTL(String prefix, String key, String value) {
        stringRedisTemplate.opsForValue()
                .set(prefix + key, value, Duration.ofSeconds(LIMIT_TIME));
    }

    @Override
    public String getValue(String prefix, String key) {
        return stringRedisTemplate.opsForValue().get(prefix + key);
    }

    @Override
    public boolean hasKey(String prefix, String key) {
        return stringRedisTemplate.hasKey(prefix + key);
    }

    @Override
    public void deleteValue(String prefix, String key) {
        stringRedisTemplate.delete(prefix + key);
    }
}
