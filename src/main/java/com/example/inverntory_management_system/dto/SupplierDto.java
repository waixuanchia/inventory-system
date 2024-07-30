package com.example.inverntory_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
public class SupplierDto {


  private Long id;

  @NotEmpty(message="name must not be empty")
  private String name;
  @NotEmpty(message="mobile number must not be empty")
  private String mobile_number;
  @NotEmpty(message="email must not be empty")
  private String email;
  @NotEmpty(message="address must not be empty")
  private String address;

  private Byte status;

  public Long createdBy;

  public Long updatedBy;

  public Date creationDate;

  public Date updatedDate;
}
