package com.example.inverntory_management_system.controller;

import com.example.inverntory_management_system.models.Supplier;
import com.example.inverntory_management_system.services.SupplierService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

  private SupplierService supplierService;

  public SupplierController(SupplierService supplierService){
    this.supplierService = supplierService;
  }

  @GetMapping(value={"","/"})
  public String getSupplier(Model model){
    List<Supplier> suppliers = this.supplierService.listSupplier();
    model.addAttribute("suppliers",suppliers);
    return "supplier/supplierList";
  }


  @GetMapping("/add-supplier")
  public String addSupplier(Model model){
    Supplier supplier = new Supplier();
    model.addAttribute("supplier",supplier);
    return "supplier/addSupplier";
  }

  @PostMapping("/add-supplier")
  public RedirectView saveSupplierAndRedirect(@ModelAttribute("supplier") Supplier supplier,
                                              RedirectAttributes attributes){
    this.supplierService.saveSupplier(supplier);
    attributes.addFlashAttribute("flash","supplier added successfully");

    return new RedirectView("/suppliers/add-supplier");

  }

  @GetMapping("/edit-supplier/{supplierId}")
  public String editSupplier(Model model,
                            @PathVariable("supplierId") Long supplierId){

    Optional<Supplier> supplierOptional = this.supplierService.findById(supplierId);
    supplierOptional.ifPresent(supplier -> model.addAttribute("supplier", supplier));
    return "supplier/editSupplier";

  }

  @PostMapping("/edit-supplier/{supplierId}")
  public RedirectView saveEditSupplier(Model model,
                             @PathVariable("supplierId") Long supplierId,
                             @ModelAttribute("supplier") Supplier supplier, RedirectAttributes attributes){

    this.supplierService.saveSupplier(supplier);
    attributes.addFlashAttribute("flash","supplier edited successfully");

    return new RedirectView(String.format("/suppliers/edit-supplier/%d",supplierId));

  }

  @GetMapping("/delete-supplier/{supplierId}")
  public RedirectView deleteSupplier(Model model,
                            @PathVariable("supplierId") Long supplierId,
                               RedirectAttributes redirectAttributes){

    Optional<Supplier> supplierOptional = this.supplierService.findById(supplierId);

    supplierOptional.ifPresent(supplier -> {
      this.supplierService.deleteSupplier(supplierId);
    });
    redirectAttributes.addFlashAttribute("flash","supplier deleted successfully");
    return new RedirectView("/suppliers");
  }
}
