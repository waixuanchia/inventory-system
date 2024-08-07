package com.example.inverntory_management_system.controller;


import com.example.inverntory_management_system.dto.UnitDto;
import com.example.inverntory_management_system.mapper.UnitMapper;
import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.services.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

  @GetMapping("/add-unit")
  public String addUnit(Model model){
    UnitDto unit = new UnitDto();
    model.addAttribute("unit",unit);
    return "unit/addUnit";
  }

  @PostMapping("/add-unit")
  public String saveUnit(@Valid @ModelAttribute("unit") UnitDto unitDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model){
    if(bindingResult.hasErrors()){
      model.addAttribute("alertType","error");
      model.addAttribute("alertMessage","There are errors in the form");
      model.addAttribute("unit",unitDto);
      return "unit/addUnit";
    }
    else{
      Unit unit = UnitMapper.mapToUnit(unitDto);
      this.unitService.saveUnit(unit);
      redirectAttributes.addFlashAttribute("alertType","success");
      redirectAttributes.addFlashAttribute("alertMessage","unit save success");

    }
    return "redirect:/units/add-unit";
  }

  @GetMapping("/edit-unit/{unitId}")
  public String editUnit(@PathVariable("unitId") Long id, Model model){
    Optional<Unit> unitOptional = this.unitService.findById(id);
    if(unitOptional.isPresent()){
      model.addAttribute("unit",unitOptional.get());
      return "unit/editUnit";
    }
    else{
      model.addAttribute("alertType","error");
      model.addAttribute("alertMessage","unit not found");
      return "redirect:/units";
    }

  }

}
