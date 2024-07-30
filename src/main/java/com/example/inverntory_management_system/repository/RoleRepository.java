package com.example.inverntory_management_system.repository;

import com.example.inverntory_management_system.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
  Role findByName(String name);
}
