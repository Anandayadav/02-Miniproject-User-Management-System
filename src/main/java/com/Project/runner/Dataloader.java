package com.Project.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Project.Entity.Cities;
import com.Project.Entity.Countries;
import com.Project.Entity.States;
import com.Project.repo.Citiesrepo;
import com.Project.repo.Countriesrepo;
import com.Project.repo.Statesrepo;

@Component
public class Dataloader implements ApplicationRunner{
	
	@Autowired
	private Countriesrepo countriesrepo;
	
	@Autowired
	private Statesrepo statesrepo;
	
	@Autowired
	private Citiesrepo citiesrepo;
	
	
	public void run(ApplicationArguments args)
	{
		countriesrepo.deleteAll();
		statesrepo.deleteAll();
		citiesrepo.deleteAll();
		
		Countries cr1=new Countries(1,"India");
		Countries cr2=new Countries(2,"USA");
		
		countriesrepo.saveAll(Arrays.asList(cr1,cr2));
		
		States s1=new States(1,"Karnataka",1);
		States s2=new States(2,"Tamilnadu",1);
		
		
		States s4=new States(3,"Texas",2);
		States s5=new States(4,"New Jersy",2);
		
		statesrepo.saveAll(Arrays.asList(s1,s2,s4,s5));
		
		
		Cities c1=new Cities(1, "Benglore", 1);
		Cities c2=new Cities(2, "Mysore", 1);
		
		Cities c3=new Cities(3, "Chennai", 2);
		Cities c4=new Cities(4, "Nellore", 2);
		
		Cities c5=new Cities(5, "Texas1", 3);
		Cities c6=new Cities(6, "Texas2", 3);
		
		Cities c7=new Cities(7, "New jersy1", 4);
		Cities c8=new Cities(8, "New Jersy2", 4);

		citiesrepo.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8));


		
	}

}
