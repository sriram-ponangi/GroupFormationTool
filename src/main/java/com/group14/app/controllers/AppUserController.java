package com.group14.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group14.app.models.AppUser;
import com.group14.app.repositories.AppUserRepository;

@RestController
@RequestMapping("/user")
public class AppUserController {
	
	@Autowired
	AppUserRepository appUserRepository;

}
