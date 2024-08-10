package com.example.inverntory_management_system.mapper;

import com.example.inverntory_management_system.dto.CategoryDto;
import com.example.inverntory_management_system.dto.UnitDto;
import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.models.Unit;

public class CategoryMapper {

  public static Category mapToCategory(CategoryDto categoryDto){
    Category category = Category.builder()
      .id(categoryDto.getId())
      .name(categoryDto.getName())
      .build();
    return category;

  }
}
