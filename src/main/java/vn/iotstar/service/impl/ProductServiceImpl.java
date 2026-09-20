package vn.iotstar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.Product;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.service.IProductService;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() { return productRepository.findAll(); }
    @Override
    public Optional<Product> findByProductName(String productName) { return productRepository.findByProductName(productName); }
    @Override
    public Optional<Product> findByCreateDate(Timestamp createDate) { return productRepository.findByCreateDate(createDate); }
    @Override
    public <S extends Product> S save(S entity) { return productRepository.save(entity); }
}