package com.Project.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.Entity.User;
import com.Project.binding.Registerform;
import com.Project.binding.Resetpswrdform;
import com.Project.repo.Userrepo;
import com.Project.utils.Emailutils;

@Service
public class Userserviceimp implements Userservice{

	@Autowired
	private Userrepo urepo; 
	
	@Autowired
	private Emailutils emailutil;

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
	public User login(User user) {
		
		User u=urepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
		return u;
	}

	@Override
	public boolean resetpassword(Resetpswrdform resetform) {
		Optional<User> u=urepo.findById(resetform.getUid());
		User usr=u.get();
		
		usr.setPassword(resetform.getNewpassword());
		usr.setpswrdupdated("YES");
		
		User ur=urepo.save(usr);
		return ur.getpswrdupdated()==resetform.getNewpassword();
	}

	@Override
	public boolean sendEmailMessage(String to, String subject, String body) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
