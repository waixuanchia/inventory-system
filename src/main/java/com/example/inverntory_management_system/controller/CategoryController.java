package com.example.inverntory_management_system.controller;

import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("")
  public String listCategories(Model model){
    List<Category> categories = this.categoryService.listCategories();
    model.addAttribute("categories",categories);
    return "category/categoryList";
  }
}
