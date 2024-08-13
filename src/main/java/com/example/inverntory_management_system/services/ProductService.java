package com.example.inverntory_management_system.services;

import com.example.inverntory_management_system.models.Product;

import java.util.List;

public interface ProductService {
  public List<Product> listProducts();

  public Product saveProduct(Product product);
}
