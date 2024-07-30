package com.example.inverntory_management_system.services;

import com.example.inverntory_management_system.dto.RegisterDto;
import com.example.inverntory_management_system.models.UserEntity;
import org.springframework.stereotype.Service;


public interface UserService {
  void saveUser(RegisterDto registerDto);

  UserEntity findByEmail(String email);

  UserEntity findByUsername(String username);
}
