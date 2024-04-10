package kimchisoup.matchingjob.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private static final String PREFIX = "sms:";
    private static final int LIMIT_TIME = 5 * 60;

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void setValueWithTTL(String key, String value) {
        stringRedisTemplate.opsForValue()
                .set(PREFIX + key, value, Duration.ofSeconds(LIMIT_TIME));
    }

    @Override
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(PREFIX + key);
    }

    @Override
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(PREFIX + key);
    }

    @Override
    public void deleteValue(String key) {
        stringRedisTemplate.delete(PREFIX + key);
    }
}
