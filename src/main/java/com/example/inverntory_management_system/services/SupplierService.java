package com.example.inverntory_management_system.services;

import com.example.inverntory_management_system.models.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

  public List<Supplier> listSupplier();
  public Supplier saveSupplier(Supplier supplier);

  public Optional<Supplier> findById(Long id);

  public void deleteSupplier(Long id);
}
