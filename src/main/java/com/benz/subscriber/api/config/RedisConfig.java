package com.benz.subscriber.api.config;

import com.benz.subscriber.api.listener.ProductConsumer;
import com.benz.subscriber.api.model.Product;
import com.benz.subscriber.api.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String,Product> redisTemplateForProduct()
    {
        RedisTemplate<String,Product> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String,Student> redisTemplateStudentForStudent()
    {
        RedisTemplate<String,Student> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }


    private Jackson2JsonRedisSerializer<Product> jackson2JsonRedisSerializer()
    {
        return new Jackson2JsonRedisSerializer<Product>(Product.class);
    }

    @Bean
    public ChannelTopic topic()
    {
        return new ChannelTopic("PRODUCT_TOPIC");
    }

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(MessageListenerAdapter messageListenerAdapter)
    {
        RedisMessageListenerContainer container=new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListenerAdapter,topic());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(ProductConsumer consumer)
    {
        MessageListenerAdapter listenerAdapter= new MessageListenerAdapter(consumer);
        listenerAdapter.setSerializer(jackson2JsonRedisSerializer());
        return listenerAdapter;
    }

    private JedisConnectionFactory jedisConnectionFactory()
    {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1",6379));
    }
}
