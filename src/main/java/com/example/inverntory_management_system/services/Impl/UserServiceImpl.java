package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.dto.RegisterDto;
import com.example.inverntory_management_system.models.Role;
import com.example.inverntory_management_system.models.UserEntity;
import com.example.inverntory_management_system.repository.RoleRepository;
import com.example.inverntory_management_system.repository.UserRepository;
import com.example.inverntory_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

private UserRepository userRepository;
private RoleRepository roleRepository;

private PasswordEncoder passwordEncoder;

@Autowired
  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void saveUser(RegisterDto registerDto) {
    UserEntity user = new UserEntity();
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(registerDto.getPassword());
    Role role = roleRepository.findByName("USER");
    user.setRoles(Arrays.asList(role));
    userRepository.save(user);
  }

  @Override
  public UserEntity findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserEntity findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
