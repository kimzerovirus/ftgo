package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsVisible(boolean isVisible);

}
