package com.example.config;

import com.example.custom.FastJson2JsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Author:Sphinx
 * Date:2019/04/11 17:24
 * Description:
 */
@Configuration
public class RedisConfig {
    //@Bean
    //public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
    //    RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<>();
    //    redisTemplate.setConnectionFactory(redisConnectionFactory);
    //    // 使用Jackson2JsonRedisSerialize 替换默认序列化
    //    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    //
    //    ObjectMapper objectMapper = new ObjectMapper();
    //    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //
    //    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    //
    //    // 设置value的序列化规则和 key的序列化规则
    //    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    //    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //    redisTemplate.afterPropertiesSet();
    //    return redisTemplate;
    //}

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        //Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        //使用Fastjson2JsonRedisSerializer来序列化和反序列化redis的value值 by zhengkai
        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }







}
