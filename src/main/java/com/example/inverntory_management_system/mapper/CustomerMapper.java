package com.example.inverntory_management_system.mapper;

import com.example.inverntory_management_system.dto.CustomerDto;
import com.example.inverntory_management_system.models.Customer;

public class CustomerMapper {

  public static Customer mapToCustomer(CustomerDto customerDto){
    Customer customer = Customer.builder()
      .id(customerDto.getId())
      .customer_image(customerDto.getCustomer_image())
      .email(customerDto.getEmail())
      .address(customerDto.getAddress())
      .name(customerDto.getName())
      .mobile_number(customerDto.getMobile_number())
      .build();
    return customer;

  }
}

