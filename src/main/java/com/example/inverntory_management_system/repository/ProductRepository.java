package com.example.inverntory_management_system.repository;

import com.example.inverntory_management_system.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
