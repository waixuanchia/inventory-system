package com.example.inverntory_management_system.services.Impl;

import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.repository.UnitRepository;
import com.example.inverntory_management_system.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {

  @Autowired
  private UnitRepository unitRepository;

  @Override
  public List<Unit> listUnits() {
    return this.unitRepository.findAll();
  }

  @Override
  public Unit saveUnit(Unit unit) {
    return this.unitRepository.save(unit);
  }

  @Override
  public Optional<Unit> findById(Long id) {
    return this.unitRepository.findById(id);
  }
}
