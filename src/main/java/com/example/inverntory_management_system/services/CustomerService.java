package com.example.inverntory_management_system.services;

import com.example.inverntory_management_system.models.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
  public List<Customer> listCustomer();

  public Customer saveCustomer(Customer customer);

  public Optional<Customer> findById(Long id);

  public String resizeImage(String upload_dir,MultipartFile image) throws IOException;

  public void deleteCustomer(Customer customer);
}
