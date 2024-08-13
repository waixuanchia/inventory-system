package com.example.inverntory_management_system.dto;

import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.models.Supplier;
import com.example.inverntory_management_system.models.Unit;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  private Long id;

  private String name;


  @NotNull(message="supplier is required")
  private Supplier supplier;

  @NotNull(message="unit is required")
  private Unit unit;

  @NotNull(message="category is required")
  private Category category;
}
