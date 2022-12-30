package me.kzv.ecommerce.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.ecommerce.controller.dtos.CategoryListDto;
import me.kzv.ecommerce.domain.entity.Category;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoryCacheRepository {
    private final RedisTemplate<String, List<Category>> categoryListRedisTemplate;

    public List<Category> save(List<Category> categoryList){
        log.info("set categoryList from redis {}", categoryList);
        categoryListRedisTemplate.opsForValue().set("categoryList",categoryList, Duration.ofDays(1));
        return categoryList;
    }

    public Optional<List<Category>> getCategoryList(){
        var data = categoryListRedisTemplate.opsForValue().get("categoryList");
        log.info("get categoryList from redis {}", data);
        return Optional.ofNullable(data);
    }
}
