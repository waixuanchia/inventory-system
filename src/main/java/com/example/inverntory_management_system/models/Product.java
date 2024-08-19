package com.example.inverntory_management_system.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToOne(cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
  @JoinColumn(name="supplier_id",referencedColumnName="id")
  private Supplier supplier;

  @OneToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="unit_id",referencedColumnName="id")
  private Unit unit;

  @OneToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="category_id",referencedColumnName="id")
  private Category category;

  private int quantity;

  @Column(name="status",nullable = true,columnDefinition="TINYINT Default 1")
  private Byte status;

  public Long createdBy;

  public Long updatedBy;

  @CreationTimestamp
  public Date creationDate;

  @UpdateTimestamp
  public Date updatedDate;


}
