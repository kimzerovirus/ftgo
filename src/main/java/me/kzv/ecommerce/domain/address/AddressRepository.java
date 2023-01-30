package me.kzv.ecommerce.domain.address;

import me.kzv.ecommerce.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
