package com.example.inverntory_management_system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

  private Long id;


  @NotBlank(message="customer name should not be blank")
  private String name;

  private String customer_image;

  @NotBlank(message="mobile number should not be blank")
  private String mobile_number;

  @NotBlank(message="email should not be blank")
  private String email;

  private String address;

  private Byte status;
}
