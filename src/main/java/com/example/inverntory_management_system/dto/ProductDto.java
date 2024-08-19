package com.example.inverntory_management_system.dto;

import com.example.inverntory_management_system.annotation.ForeignKeyRequired;
import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.models.Supplier;
import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.repository.CategoryRepository;
import com.example.inverntory_management_system.repository.SupplierRepository;
import com.example.inverntory_management_system.repository.UnitRepository;
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
  @ForeignKeyRequired(repository = SupplierRepository.class, entityType = Supplier.class)
  private Supplier supplier;

  @NotNull(message="unit is required")
  @ForeignKeyRequired(repository = UnitRepository.class, entityType = Unit.class)
  private Unit unit;

  @NotNull(message="category is required")
  @ForeignKeyRequired(repository = CategoryRepository.class, entityType = Category.class)
  private Category category;
}
