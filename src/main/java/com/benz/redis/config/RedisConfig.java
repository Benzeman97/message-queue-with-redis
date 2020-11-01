package com.benz.redis.config;

import com.benz.redis.model.Product;
import com.benz.redis.model.Student;
import com.benz.redis.model.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String,User> getUser()
    {
        RedisTemplate<String,User> redisTemplate=new RedisTemplate<String, User>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
       // redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        return  redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Student> getStudentConfig()
    {
        RedisTemplate<String,Student> redisTemplate=new RedisTemplate<String,Student>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        //redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        return  redisTemplate;
    }

    private Jackson2JsonRedisSerializer<Product> jackson2JsonRedisSerializerForProduct()
    {
        return new Jackson2JsonRedisSerializer<Product>(Product.class);
    }

    @Bean
    public RedisTemplate<String,Product> getProductConfig()
    {
        RedisTemplate<String,Product> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializerForProduct());
        return  redisTemplate;
    }

    @Bean
    public ChannelTopic topic()
    {
        return new ChannelTopic("PRODUCT_TOPIC");
    }

    private JedisConnectionFactory getJedisConnectionFactory()
    {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1",6379));
    }
}
