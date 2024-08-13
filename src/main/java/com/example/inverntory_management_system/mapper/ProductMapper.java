package com.example.inverntory_management_system.mapper;

import com.example.inverntory_management_system.dto.ProductDto;
import com.example.inverntory_management_system.models.Product;

public class ProductMapper {
  public static Product mapToProduct(ProductDto productDto){
    return Product
      .builder()
      .id(productDto.getId())
      .name(productDto.getName())
      .supplier(productDto.getSupplier())
      .category(productDto.getCategory())
      .unit(productDto.getUnit())
      .build();
  }
}
