package com.example.inverntory_management_system.controller;

import com.example.inverntory_management_system.models.Customer;
import com.example.inverntory_management_system.services.CustomerService;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/customers")
public class CustomerController {

  private static String upload_dir = "./src/main/resources/static/assets/upload/";
  @Autowired
  private CustomerService customerService;

  @GetMapping("")
  public String displayCustomer(Model model){
    List<Customer> customers = this.customerService.listCustomer();
    model.addAttribute("customers",customers);
    return "customer/customerList";
  }

  @GetMapping("/add-customer")
  public String addCustomer(Model model){
    Customer customer = new Customer();
    model.addAttribute("customer",customer);
    return "customer/addCustomer";
  }

  @PostMapping("/add-customer")
  public RedirectView saveCustomer(
    @ModelAttribute("customer") Customer customer,
    @RequestParam("customerImage") MultipartFile file,
    RedirectAttributes attributes){
    try {

      String filename = file.getOriginalFilename();
      String outputFilename =
        UUID.randomUUID().toString() +
          "." +
        filename.substring(filename.lastIndexOf(".") + 1);
      Thumbnails.of(file.getInputStream())
        .size(100,100)
        .toFile(upload_dir + outputFilename);

      customer.setCustomer_image(outputFilename);
      attributes.addFlashAttribute("flash","customer added successfully");
      this.customerService.saveCustomer(customer);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return new RedirectView("/customers/add-customer");
  }

}
