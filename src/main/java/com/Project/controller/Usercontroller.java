package com.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Project.Entity.User;
import com.Project.binding.Registerform;
import com.Project.binding.Resetpswrdform;
import com.Project.service.Userservice;

@Controller
public class Usercontroller {

	@Autowired
	private Userservice uservice;
	
	
	@GetMapping("/")
	public String loginPage(Model model)
	{
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		model.addAttribute("registerform", new Registerform());
		return "register";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model)
	{
		
		return "dashboard";
	}
	
	@GetMapping("/")
	public String resetpassword(Registerform rgform, Model model)
	{
		model.addAttribute("resetpswrdform", new Resetpswrdform());
		
		User user=uservice.findUser(rgform.getEmail());
		if(user==null)
		{
			uservice.saveUser(rgform);
			model.addAttribute("msg","You are registered successfully......");
		}
		else {
			model.addAttribute("msg","Please change the email...");
		}
		return "login";
	}
	
	
	
	
	
	
	
}
