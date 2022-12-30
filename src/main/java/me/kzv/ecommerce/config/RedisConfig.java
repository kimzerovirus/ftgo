package me.kzv.ecommerce.config;

import me.kzv.ecommerce.controller.dtos.CategoryListDto;
import me.kzv.ecommerce.domain.entity.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;

// webflux 기반에서는 reactive 모듈 필요
// https://pearlluck.tistory.com/727
// https://docs.spring.io/spring-data/redis/docs/current/reference/html/
// https://www.notion.so/Redis-4ab3e691d9a8494fb3bbc680962ba0c6 - 우아한 레디스

//@EnableCaching
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, List<Category>> categoryListDtoRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, List<Category>> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(List.class));
        return redisTemplate;
    }

//    //레디스 캐시 https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/cache/RedisCacheManager.html
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
////                .entryTtl(Duration.ofDays(1)) // 하루 캐싱
//                .serializeKeysWith(RedisSerializationContext
//                        .SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext
//                        .SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//
//        return RedisCacheManager
//                .RedisCacheManagerBuilder
//                .fromConnectionFactory(redisConnectionFactory)
//                .cacheDefaults(redisCacheConfiguration)
//                .build();
//    }
}
