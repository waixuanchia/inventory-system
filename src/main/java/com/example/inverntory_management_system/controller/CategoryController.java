package com.example.inverntory_management_system.controller;

import com.example.inverntory_management_system.dto.CategoryDto;
import com.example.inverntory_management_system.dto.UnitDto;
import com.example.inverntory_management_system.mapper.CategoryMapper;
import com.example.inverntory_management_system.mapper.UnitMapper;
import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

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

  @GetMapping("/add-category")
  public String addCategory(Model model){
    CategoryDto category = new CategoryDto();
    model.addAttribute("category",category);
    return "category/addCategory";
  }

  @PostMapping("/add-category")
  public String saveUnit(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model){
    if(bindingResult.hasErrors()){
      model.addAttribute("alertType","error");
      model.addAttribute("alertMessage","There are errors in the form");
      model.addAttribute("category",categoryDto);
      return "category/addCategory";
    }
    else{
      Category category = CategoryMapper.mapToCategory(categoryDto);
      this.categoryService.saveCategory(category);
      redirectAttributes.addFlashAttribute("alertType","success");
      redirectAttributes.addFlashAttribute("alertMessage","category save success");

    }
    return "redirect:/category/add-category";
  }

  @GetMapping("/edit-category/{categoryId}")
  public String editCategory(@PathVariable("categoryId") Long id, Model model){
    Optional<Category> categoryOptional = this.categoryService.findById(id);
    if(categoryOptional.isPresent()){
      model.addAttribute("category",categoryOptional.get());
      return "category/editCategory";
    }
    else{
      model.addAttribute("alertType","error");
      model.addAttribute("alertMessage","category not found");
      return "redirect:/category";
    }

  }

  @PostMapping("/edit-category/{categoryId}")
  public String editCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                         BindingResult bindingResult,
                         @PathVariable("categoryId") Long categoryId,
                         RedirectAttributes redirectAttributes,
                         Model model){

    if(bindingResult.hasErrors()){
      model.addAttribute("alertType","error");
      model.addAttribute("alertMessage","There are errors in the form");
      model.addAttribute("category",categoryDto);
      return "category/editCategory";
    }
    else{
      Category category = CategoryMapper.mapToCategory(categoryDto);
      this.categoryService.saveCategory(category);
      redirectAttributes.addFlashAttribute("alertType","success");
      redirectAttributes.addFlashAttribute("alertMessage","category save success");
      return "redirect:/category/edit-category/" + categoryId;
    }

  }

  @GetMapping("/delete-category/{categoryId}")
  public RedirectView deleteCategory(@PathVariable("categoryId") Long categoryId,
                                 RedirectAttributes redirectAttributes){
    Optional<Category> categoryOptional = this.categoryService.findById(categoryId);
    if(categoryOptional.isPresent()){
      this.categoryService.deleteCategory(categoryOptional.get());
      redirectAttributes.addFlashAttribute("alertType","success");
      redirectAttributes.addFlashAttribute("alertMessage","successfully deleted category");

    }
    else{
      redirectAttributes.addFlashAttribute("alertType","error");
      redirectAttributes.addFlashAttribute("alertMessage","category not found");
    }

    return new RedirectView("/category");
  }
}
