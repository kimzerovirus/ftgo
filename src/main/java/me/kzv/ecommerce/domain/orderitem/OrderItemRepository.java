package me.kzv.ecommerce.domain.orderitem;

import me.kzv.ecommerce.domain.orderitem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
