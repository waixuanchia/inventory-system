package com.example.inverntory_management_system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="suppliers")
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="name",nullable=true)
  private String name;
  @Column(name="mobile_number",nullable = true)
  private String mobile_number;
  @Column(name="email",nullable = true)
  private String email;
  @Column(name="address",nullable = true)
  private String address;
  @Column(name="status",nullable = true,columnDefinition="TINYINT Default 1")
  private Byte status;

  public Long createdBy;

  public Long updatedBy;

  @CreationTimestamp
  public Date creationDate;

  @UpdateTimestamp
  public Date updatedDate;
}
