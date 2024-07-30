package com.example.inverntory_management_system.repository;

import com.example.inverntory_management_system.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
