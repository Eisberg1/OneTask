package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Author:Sphinx
 * Date:2019/03/26 15:03
 * Description:
 */

@Component
public final class RedisUtil {

    @Autowired
    StringRedisTemplate srt;

    public void set(String key,String value){
        srt.opsForValue().set(key,value);
    }
    public void set(String key,String value,long time){
        if (time>0){
            srt.opsForValue().set(key,value,time, TimeUnit.SECONDS);
        }else{
            srt.opsForValue().set(key,value);
        }
    }








}
