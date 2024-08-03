package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.models.Customer;
import com.example.inverntory_management_system.repository.CustomerRepository;
import com.example.inverntory_management_system.services.CustomerService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> listCustomer() {
    return this.customerRepository.findAll();
  }

  @Override
  public Customer saveCustomer(Customer customer) {
    return this.customerRepository.save(customer);
  }

  @Override
  public Optional<Customer> findById(Long id) {
    return this.customerRepository.findById(id);
  }

  @Override
  public String resizeImage(String upload_dir,MultipartFile image) throws IOException {
    String filename = image.getOriginalFilename();
    String outputFilename =
      UUID.randomUUID().toString() +
        "." +
        filename.substring(filename.lastIndexOf(".") + 1);
    Thumbnails.of(image.getInputStream())
      .size(100,100)
      .toFile(upload_dir + outputFilename);
    return outputFilename;
  }


}
