package com.Project.controller;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Project.Entity.User;
import com.Project.binding.Loginform;
import com.Project.binding.Registerform;
import com.Project.binding.Resetpswrdform;
import com.Project.props.AppProps;
import com.Project.service.Dashboardservice;
import com.Project.service.Userservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Usercontroller {

	@Autowired
	private Userservice uservice;
	
	@Autowired
	private Dashboardservice dashservice;
	
	@Autowired
	private AppProps props;
	

	
	@GetMapping("/")
	public String loginPage(Model model)
	{
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/registerPage")
	public String registerPage(Model model)
	{
		model.addAttribute("registerform", new Registerform());
		
		
		Map<Integer,String>countries=uservice.retrievecountries();
		model.addAttribute("countries", countries);
		
		return "register";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model)
	{
		String quotes=dashservice.getQuote();
		model.addAttribute("quotes", quotes);

		return "dashboard";
	}
	
	@GetMapping("/resetpasswordPage")
	public String  resetPasswordPage(HttpServletRequest req,Model model)
	{
		model.addAttribute("resetpswrdform", new Resetpswrdform());
		HttpSession session=req.getSession(false);
		model.addAttribute("email", session.getAttribute("email"));
		return "resetpassword";
		
	}
	
	
	@PostMapping("/register")
	public String registerUser(Registerform rgform, Model model)
	{
		model.addAttribute("registerform", new Registerform());
		
		User user=uservice.findUser(rgform.getEmail());
		
		if(user!=null)
		{
			Map<Integer,String>countries=uservice.retrievecountries();
			model.addAttribute("countries", countries);
			Map<String,String>msgs=props.getMessages();
			model.addAttribute("errMsg", msgs.get("dupilcateemail"));
			return "register";
		}
		boolean status=uservice.saveUser(rgform);
		
		if(status)
		{
			return "redirect:dashboard";
		}
		
		return "register";
	}
	
	@PostMapping("/do-login")
	public String doLogin(HttpServletRequest req,Loginform login,Model model)
	{
		model.addAttribute("loginform", new Loginform());
		
		Map<String,String>msg=props.getMessages();
		HttpSession session=req.getSession(true);
	
			User user=uservice.login(login);
			
			if(user!=null)
			{
				session.setAttribute("email", user.getEmail());
				if(user.getpswrdupdated().equals("NO"))
				{
				
					return "redirect:resetpasswordPage";
				}
				else
				{
					return "redirect:dashboard";
				}
			}
			
			Map<String,String> msgs=props.getMessages();
			model.addAttribute("errMsg", msgs.get("invalidlogin"));
		return "login";
	}
	
	@PostMapping("/reset-password")
	public String  resetPassword(Resetpswrdform resetform,Model model)
	{
		model.addAttribute("resetpswrdform", new Resetpswrdform());
		
		Resetpswrdform resetpswrdform=new Resetpswrdform();
		BeanUtils.copyProperties(resetform, resetpswrdform);
		resetpswrdform.setEmail(resetform.getEmail());

		if(resetpswrdform.getNewpassword()==resetpswrdform.getConfirmpassword())
		{
			boolean status=uservice.resetpassword(resetpswrdform);
		
			if(status)
			{
				return "redirect:dashboard";
			}
		}
			Map<String,String>msgs=props.getMessages();
			model.addAttribute("errMsg", msgs.get("resetpasswordfailure"));
		return "resetpassword";
		
	}
	
	@GetMapping("/logout")
	public String logOut(HttpServletRequest req)
	{
		HttpSession session=req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer,String> getstates(@RequestParam("crid") Integer crid)
	{
		
		return uservice.retrievestates(crid);
	}
	

	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer,String> getcities(@RequestParam("sid") Integer sid)
	{
	
		
		return uservice.retrievecities(sid);
	}
	
}
