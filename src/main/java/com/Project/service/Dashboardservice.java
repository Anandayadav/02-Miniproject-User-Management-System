package com.Project.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Dashboardservice {

	
	
	private static final String Quote_endpoint="https://type.fit/api/quotes";
	
	public static Map<String, String> getQuote()
	{
		RestTemplate rt=new RestTemplate();
	
		
		ResponseEntity<Map[]>responseentity=rt.getForEntity(Quote_endpoint, Map[].class);
		
		Map<String,String>[]quotesmaparry=responseentity.getBody();
		
		Random random=new Random();	
		Integer randomnum=random.nextInt(quotesmaparry.length);
		
		Map<String,String>selectquote=quotesmaparry[randomnum];
		
		return selectquote;
		
	}

}
