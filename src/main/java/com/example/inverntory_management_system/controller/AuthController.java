package com.example.inverntory_management_system.controller;

import com.example.inverntory_management_system.dto.RegisterDto;
import com.example.inverntory_management_system.models.UserEntity;
import com.example.inverntory_management_system.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/auth")
public class AuthController {
  private UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value="/login",method={RequestMethod.GET, RequestMethod.POST})
  public String login(){
    return "auth/login";
  }

  @GetMapping("/register")
  public String register(Model model){
    RegisterDto user = new RegisterDto();
    model.addAttribute("user",user);
    return "auth/register";
  }

  @PostMapping("/saveUser")
  public String saveUser(@Valid @ModelAttribute("user") RegisterDto user,
                               BindingResult binding,
                               Model model,
                               RedirectAttributes redirectAttributes){
    UserEntity existingUser = userService.findByEmail(user.getEmail());
    if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
      binding.rejectValue("email","email provided already exists");
    }

    existingUser = userService.findByUsername(user.getUsername());
    if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()){
      binding.rejectValue("username","username provided already exists");
    }

    if(binding.hasErrors()){

      return "auth/register";
    }
    else{
      redirectAttributes.addAttribute("success","true");
      userService.saveUser(user);
      return "redirect:/auth/register";
    }


  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/auth/login?logout=true";
  }
}
