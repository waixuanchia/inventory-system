package com.example.inverntory_management_system.repository;

import com.example.inverntory_management_system.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
  UserEntity findByEmail(String email);

  UserEntity findByUsername(String userName);
}
