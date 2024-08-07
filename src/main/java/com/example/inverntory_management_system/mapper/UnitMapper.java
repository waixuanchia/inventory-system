package com.example.inverntory_management_system.mapper;

import com.example.inverntory_management_system.dto.CustomerDto;
import com.example.inverntory_management_system.dto.UnitDto;
import com.example.inverntory_management_system.models.Customer;
import com.example.inverntory_management_system.models.Unit;

public class UnitMapper {

  public static Unit mapToUnit(UnitDto unitDto){
    Unit unit = Unit.builder()
      .id(unitDto.getId())
      .name(unitDto.getName())
      .build();
    return unit;

  }
}
