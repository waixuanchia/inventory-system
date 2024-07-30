package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.models.Customer;
import com.example.inverntory_management_system.repository.CustomerRepository;
import com.example.inverntory_management_system.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;


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


}
