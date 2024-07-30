package com.example.inverntory_management_system.repository;

import com.example.inverntory_management_system.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
