package com.example.inverntory_management_system.controller;


import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/units")
public class UnitController {

  @Autowired
  private UnitService unitService;

  @GetMapping("")
  public String listUnits(Model model){
    List<Unit> units = this.unitService.listUnits();
    model.addAttribute("units",units);
    return "unit/unitList";
  }

}
