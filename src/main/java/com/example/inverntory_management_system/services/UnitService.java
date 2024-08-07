package com.example.inverntory_management_system.services;

import com.example.inverntory_management_system.models.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
  public List<Unit> listUnits();

  public Unit saveUnit(Unit unit);

  public Optional<Unit> findById(Long id);

  public void deleteUnit(Unit unit);
}
