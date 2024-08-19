package com.example.inverntory_management_system.controller;


import com.example.inverntory_management_system.dto.ProductDto;
import com.example.inverntory_management_system.mapper.ProductMapper;
import com.example.inverntory_management_system.models.Category;
import com.example.inverntory_management_system.models.Product;
import com.example.inverntory_management_system.models.Supplier;
import com.example.inverntory_management_system.models.Unit;
import com.example.inverntory_management_system.services.CategoryService;
import com.example.inverntory_management_system.services.ProductService;
import com.example.inverntory_management_system.services.SupplierService;
import com.example.inverntory_management_system.services.UnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private SupplierService supplierService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private UnitService unitService;

  @GetMapping("")
  public String listProducts(Model model){
    List<Product> products = this.productService.listProducts();
    model.addAttribute("products",products);
    return "product/productList";
  }

  @GetMapping("/add-product")
  public String addProduct(Model model){
    ProductDto product = new ProductDto();
    List<Supplier> suppliers = this.supplierService.listSupplier();
    List<Category> categories = this.categoryService.listCategories();
    List<Unit> units = this.unitService.listUnits();
    model.addAttribute("product",product);
    model.addAttribute("suppliers",suppliers);
    model.addAttribute("categories",categories);
    model.addAttribute("units",units);
    return "product/addProduct";
  }

  @PostMapping("/add-product")
  public String saveProduct(@Valid @ModelAttribute("product") ProductDto productDto,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes){
    if(bindingResult.hasErrors()){
      model.addAttribute("alertType","error");
      model.addAttribute("alertMessage","There are errors within the form");
      List<Supplier> suppliers = this.supplierService.listSupplier();
      List<Category> categories = this.categoryService.listCategories();
      List<Unit> units = this.unitService.listUnits();
      model.addAttribute("suppliers",suppliers);
      model.addAttribute("categories",categories);
      model.addAttribute("units",units);
      return "product/addProduct";
    }
    else{
      Product product = ProductMapper.mapToProduct(productDto);
      this.productService.saveProduct(product);
      redirectAttributes.addFlashAttribute("alertType","success");
      redirectAttributes.addFlashAttribute("alertMessage","product save success");
      return "redirect:/product/add-product";
    }
  }

  @GetMapping("/edit-product/{productId}")
  public String editProduct(Model model){
    ProductDto product = new ProductDto();
    List<Supplier> suppliers = this.supplierService.listSupplier();
    List<Category> categories = this.categoryService.listCategories();
    List<Unit> units = this.unitService.listUnits();
    model.addAttribute("product",product);
    model.addAttribute("suppliers",suppliers);
    model.addAttribute("categories",categories);
    model.addAttribute("units",units);
    return "product/addProduct";
  }
}
