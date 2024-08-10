package com.example.inverntory_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDto {
  private Long id;
  @NotBlank(message="Category name cannot be blank")
  private String name;
}
