package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.models.Supplier;
import com.example.inverntory_management_system.repository.SupplierRepository;
import com.example.inverntory_management_system.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
  private SupplierRepository supplierRepository;
  public SupplierServiceImpl(SupplierRepository supplierRepository){
    this.supplierRepository = supplierRepository;
  }

  @Override
  public List<Supplier> listSupplier() {
    return this.supplierRepository.findAll();
  }

  public Supplier saveSupplier(Supplier supplier){

    return this.supplierRepository.save(supplier);
  }

  public Optional<Supplier> findById(Long id){
    return this.supplierRepository.findById(id);
  }

  @Override
  public void deleteSupplier(Long id) {
    Optional<Supplier> optionalSupplier = this.findById(id);
    optionalSupplier.ifPresent((supplier) -> {
      this.supplierRepository.delete(supplier);
    });
  }

}
