package vn.iotstar.service;

import vn.iotstar.entity.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Optional<Category> findByCategoryName(String categoryName);
    <S extends Category> S save(S entity);
    void delete(Category entity);
}