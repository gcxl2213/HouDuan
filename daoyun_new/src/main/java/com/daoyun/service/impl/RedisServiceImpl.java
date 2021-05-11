package com.daoyun.service.impl;

import com.daoyun.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value,int minute) {
        //设置过期时间为5分钟
        stringRedisTemplate.opsForValue().set(key,value,minute*60, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);

    }


    @Override
    public boolean containsValueKey(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Throwable e) {
            System.out.println(e);
        }
        return false;
    }

}
