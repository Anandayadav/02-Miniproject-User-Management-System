package com.Project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.Entity.Cities;
import com.Project.Entity.Countries;
import com.Project.Entity.States;
import com.Project.Entity.User;
import com.Project.binding.Loginform;
import com.Project.binding.Registerform;
import com.Project.binding.Resetpswrdform;
import com.Project.repo.Citiesrepo;
import com.Project.repo.Countriesrepo;
import com.Project.repo.Statesrepo;
import com.Project.repo.Userrepo;
import com.Project.utils.Emailutils;

@Service
public class Userserviceimp implements Userservice{

	@Autowired
	private Userrepo urepo; 
	
	@Autowired
	private Emailutils emailutil;
	
	@Autowired
	private Countriesrepo countryrepo; 
	

	@Autowired
	private Statesrepo statesrepo; 
	

	@Autowired
	private Citiesrepo citiesrepo; 
	
	

	@Override
	public Map<Integer, String> retrievecountries() {
		List<Countries>findall= countryrepo.findAll();
		
		Map<Integer,String>countries=new HashMap<>();
		
		findall.forEach(c->countries.put(c.getCrid(), c.getCountries()));
		return countries;
	}

	@Override
	public Map<Integer, String> retrievestates(Integer crid) {
		List<States>findstates=statesrepo.findByCrid(crid);
		
		Map<Integer,String>states=new HashMap<>();
		
		findstates.forEach(s->states.put(s.getSid(),s.getStates()));
		
		return states;
	}

	@Override
	public Map<Integer, String> retrievecities(Integer sid) {
		
		List<Cities>findcities=citiesrepo.findBySid(sid);
		
		Map<Integer,String>cities=new HashMap<>();
		
		findcities.forEach(ct->cities.put(ct.getCid(), ct.getCities()));
		
		
		return cities;
	}	
	
	
	
	
	
	
	@Override
	public User findUser(String email) {
		
		User u=urepo.findByEmail(email);
		
		return u;
	}

	@Override
	public boolean saveUser(Registerform rgformobj) {
		
		User user=new User();
		BeanUtils.copyProperties(rgformobj, user);
		
		Random rdm=new Random();
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789abcdefghijklmnopqrstuvwxyz";
		
		StringBuilder sb=new StringBuilder(6);
		
		for (int i = 0; i < 5; i++) {
			int rmdnum=rdm.nextInt(s.length()-1);
			Character c= s.charAt(rmdnum);
			sb.append(c);
		}
		String rmpswrd=sb.toString();
		user.setPassword(rmpswrd);
		user.setpswrdupdated("NO");
		
		User svuser=urepo.save(user);
		if(svuser.getUid()!=null)
		{
			String subj="Your password";
			String body="<h1>Your password is: "+svuser.getPassword()+"<h1>";
			
			return emailutil.sendMail(svuser.getEmail(), subj,body );
		}
		
		return false;
	}

	@Override
	public User login(Loginform login) {
		
		User u=urepo.findByEmailAndPassword(login.getEmail(),login.getPassword());
		return u;
	}

	@Override
	public boolean resetpassword(Resetpswrdform resetform) {
		User usr=urepo.findByEmail(resetform.getEmail());
		
		
		usr.setPassword(resetform.getNewpassword());
		usr.setpswrdupdated("YES");
		
		User ur=urepo.save(usr);
		return ur.getPassword()==resetform.getNewpassword();
	}


	}
