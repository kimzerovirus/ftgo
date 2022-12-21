package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
