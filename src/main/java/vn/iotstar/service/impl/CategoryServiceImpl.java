package vn.iotstar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.CategoryRepository;
import vn.iotstar.service.ICategoryService;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() { return categoryRepository.findAll(); }
    @Override
    public Optional<Category> findById(Long id) { return categoryRepository.findById(id); }
    @Override
    public Optional<Category> findByCategoryName(String categoryName) { return categoryRepository.findByCategoryName(categoryName); }
    @Override
    public <S extends Category> S save(S entity) { return categoryRepository.save(entity); }
    @Override
    public void delete(Category entity) { categoryRepository.delete(entity); }
}