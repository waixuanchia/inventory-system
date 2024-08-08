package com.example.inverntory_management_system.repository;

import com.example.inverntory_management_system.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
