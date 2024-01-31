package com.Project.service;

import java.util.Map;

import com.Project.Entity.User;
import com.Project.binding.Loginform;
import com.Project.binding.Registerform;
import com.Project.binding.Resetpswrdform;

public interface Userservice {

	public Map<Integer,String>retrievecountries();
	
	public Map<Integer,String>retrievestates(Integer crid);
	
	public Map<Integer,String>retrievecities(Integer sid);
	
	
	public User findUser(String email);
	
	public boolean saveUser(Registerform rgform);
	
	public User login(Loginform login);
	
	public boolean resetpassword(Resetpswrdform resetform);
	
}
