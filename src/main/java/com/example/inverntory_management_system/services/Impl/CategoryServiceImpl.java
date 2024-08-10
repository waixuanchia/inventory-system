package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.repository.CategoryRepository;
import com.example.inverntory_management_system.repository.UnitRepository;
import com.example.inverntory_management_system.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Category> listCategories() {
    return this.categoryRepository.findAll();
  }

  @Override
  public Category saveCategory(Category category) {
    return this.categoryRepository.save(category);
  }

  @Override
  public Optional<Category> findById(Long id) {
    return this.categoryRepository.findById(id);
  }

  @Override
  public void deleteCategory(Category category) {
    this.categoryRepository.delete(category);
  }
}
