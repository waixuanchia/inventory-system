package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.models.Product;
import com.example.inverntory_management_system.repository.ProductRepository;
import com.example.inverntory_management_system.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;
  @Override
  public List<Product> listProducts() {
    return this.productRepository.findAll();
  }

  @Override
  public Product saveProduct(Product product) {
    return this.productRepository.save(product);
  }
}
