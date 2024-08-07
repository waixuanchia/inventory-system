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
public class UnitDto {

  private Long id;
  @NotBlank(message="Unit name cannot be blank")
  private String name;
}
