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
@Table(name="purchases")
public class Purchase {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name="supplier_id",referencedColumnName="id")
  private Supplier supplier;

  @ManyToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="category_id",referencedColumnName="id")
  private Category category;

  @ManyToOne(cascade=CascadeType.MERGE)
  @JoinColumn(name="product_id",referencedColumnName="id")
  private Product product;

  private String purchase_no;

  private Date date;

  private String description;

  private int purchase_quantity;

  private double unit_price;

  private double price;

  @Column(name="status",nullable = true,columnDefinition="TINYINT Default 1")
  private Byte status;

  public Long createdBy;

  public Long updatedBy;

  @CreationTimestamp
  public Date creationDate;

  @UpdateTimestamp
  public Date updatedDate;
}
