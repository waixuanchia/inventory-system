package com.example.inverntory_management_system.services;



import com.example.inverntory_management_system.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
  public List<Category> listCategories();

  public Category saveCategory(Category category);

  public Optional<Category> findById(Long id);

  public void deleteCategory(Category category);
}
