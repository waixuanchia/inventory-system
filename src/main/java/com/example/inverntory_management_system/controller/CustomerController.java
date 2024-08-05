package com.example.inverntory_management_system.controller;

import com.example.inverntory_management_system.dto.CustomerDto;
import com.example.inverntory_management_system.mapper.CustomerMapper;
import com.example.inverntory_management_system.models.Customer;
import com.example.inverntory_management_system.services.CustomerService;
import jakarta.validation.Valid;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
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

  @GetMapping("/edit-customer/{customerId}")
  public String editCustomer(@PathVariable("customerId") Long customerId, Model model, Principal principal){

    Optional<Customer> customerOptional = this.customerService.findById(customerId);
    if(customerOptional.isPresent()){
      model.addAttribute("customer",customerOptional.get());
    }
    return "/customer/editCustomer";
  }

  @PostMapping("/edit-customer/{customerId}")
  public String saveEditedCustomer(@PathVariable("customerId") Long customerId,
                                   @Valid @ModelAttribute("customer") CustomerDto customerDto,
                                   BindingResult bindingResult,
                                   @RequestParam("customerImage") MultipartFile image,
                                   Model model,
                                   RedirectAttributes redirectAttributes){
    try {

      if(bindingResult.hasErrors()){

        redirectAttributes.addFlashAttribute("field_error",true);
        model.addAttribute("customer",customerDto);
      }
      else{
        Customer customer = CustomerMapper.mapToCustomer(customerDto);

        if(!image.getOriginalFilename().isEmpty()){
          String url = this.customerService.resizeImage(upload_dir,image);
          customer.setCustomer_image(url);
        }
        this.customerService.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("success",true);
      }

      return "customer/editCustomer";

    } catch (IOException e) {
      redirectAttributes.addFlashAttribute("error",true);
      return "redirect:" + String.format("/customers/edit-customer/%d",customerId);
    }
  }

  @GetMapping("/delete-customer/{customerId}")
  public RedirectView deleteCustomer(@PathVariable("customerId") Long customerId,
                                     RedirectAttributes redirectAttributes){
    Optional<Customer> customerOptional = this.customerService.findById(customerId);
    customerOptional.ifPresentOrElse((customer) -> {
      String customerImage = customer.getCustomer_image();
      try {
        Files.delete(Paths.get(upload_dir + customerImage));
        this.customerService.deleteCustomer(customer);
        redirectAttributes.addFlashAttribute("toastAlert","success");
        redirectAttributes.addFlashAttribute("toastMessage","Successfully deleted customer");

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }, () -> {
      redirectAttributes.addFlashAttribute("toastAlert","error");
      redirectAttributes.addFlashAttribute("toastMessage","Customer not found");
    });
    return new RedirectView("/customers");
  }

}
