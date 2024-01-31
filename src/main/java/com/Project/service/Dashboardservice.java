package com.Project.service;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Project.binding.Quote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Dashboardservice {

	private  String quoteurl="https://type.fit/api/quotes";
	private Quote[] quotes = null;

	
	public  String getQuote()
	{
		if(quotes==null)
		{
		RestTemplate rt=new RestTemplate();
	
		
		ResponseEntity<String>forentity=rt.getForEntity(quoteurl, String.class);
		
		String body=forentity.getBody();
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			quotes=mapper.readValue(body, Quote[].class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		Random random=new Random();	
		int randomnum=random.nextInt(quotes.length-1);
	
		
		return quotes[randomnum].getText();
		
	}

}
