package com.yunlong.redis;

import com.yunlong.api.IRedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("RedisService")
public class RedisService implements IRedisService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Override
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void setSession(String key, String value) {
        redisTemplate.opsForValue().set(key,value,60*60*24*7, TimeUnit.SECONDS);
    }

    public Object executeRedisScript(List input, String scriptName){
        DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptName)));
        defaultRedisScript.setResultType(Object.class);
        Object o = redisTemplate.execute(defaultRedisScript,input);

        return o;
    }
}
