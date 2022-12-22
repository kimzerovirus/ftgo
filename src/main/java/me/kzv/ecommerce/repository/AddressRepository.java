package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
