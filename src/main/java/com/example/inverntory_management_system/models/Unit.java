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
@Table(name="units")
public class Unit {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name="status",nullable = true,columnDefinition="TINYINT Default 1")
  private Byte status;

  public Long createdBy;

  public Long updatedBy;

  @CreationTimestamp
  public Date creationDate;

  @UpdateTimestamp
  public Date updatedDate;
}
