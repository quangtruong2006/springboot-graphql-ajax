package vn.iotstar.service;

import vn.iotstar.entity.Product;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByCreateDate(Timestamp createDate);
    <S extends Product> S save(S entity);
}