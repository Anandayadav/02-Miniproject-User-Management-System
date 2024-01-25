package com.Project.service;

import java.util.Optional;

import com.Project.Entity.User;
import com.Project.binding.Registerform;
import com.Project.binding.Resetpswrdform;

public interface Userservice {

	public User findUser(String email);
	
	public boolean saveUser(Registerform rgform);
	
	public User login(User user);
	
	public boolean resetpassword(Resetpswrdform resetform);
	
	public boolean sendEmailMessage(String to,String subject,String body);
	
}
