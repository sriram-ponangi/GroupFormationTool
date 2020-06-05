package com.group14.app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//import com.example.demo.UserRepository;
import com.group14.app.models.RegUser;
import com.group14.app.repositories.UserRepository;

@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class RegisterController {

	@Autowired
	UserRepository ur;
	 @GetMapping("/register")
	  public String greetingForm(Model model) {
	    model.addAttribute("greeting", new RegUser());
	    return "register";
	  }

	  @PostMapping("/confirm")
	  public String greetingSubmit(@ModelAttribute RegUser greeting) {
		  
		  ur.addUser(greeting.getBannerNo(), greeting.getFirstName(), greeting.getLastName(), greeting.getEmail(), greeting.getPassword());
		  return "result";
	  }
	    


}