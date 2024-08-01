package com.example.inverntory_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

  @RequestMapping("/")
  public String dashboard(@ModelAttribute("auth") String success){

    return "index.html";
  }
}
