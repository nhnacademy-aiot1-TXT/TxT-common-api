package kr.co.contxt.commonapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * token이 저장된 redis와 관련된 서비스입니다.
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplatePlace;

    public void setValue(String key, String value) {
        redisTemplatePlace.opsForValue().set(key, value);
    }
}
