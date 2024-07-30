package com.example.inverntory_management_system.services;

import com.example.inverntory_management_system.models.Customer;

import java.util.List;

public interface CustomerService {
  public List<Customer> listCustomer();

  public Customer saveCustomer(Customer customer);
}
