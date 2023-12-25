package project.backend.global.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public <T> T getRedisValue(String key, Class<T> classType) {
        String redisValue = (String) redisTemplate.opsForValue().get(key);
        if(ObjectUtils.isEmpty(redisValue)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(redisValue, classType);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void putRedis(String key, Object classType){
        try {
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(classType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getRedisValueToList(String key, Class<T> classType) {
        String redisValue = (String) redisTemplate.opsForValue().get(key);
        if(ObjectUtils.isEmpty(redisValue)) {
            return null;
        } else {
            try {
                return Arrays.asList(objectMapper.readValue(redisValue, new TypeReference<T>() {}));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void putListToRedis(String key, List<?> classType){
        try {
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(classType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}