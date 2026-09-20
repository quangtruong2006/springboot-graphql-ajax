package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.entity.Product;
import java.sql.Timestamp;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByCreateDate(Timestamp createDate);
}