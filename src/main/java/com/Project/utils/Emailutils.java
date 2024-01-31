package com.Project.utils;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class Emailutils {

	@Autowired
	private JavaMailSender mailsender;
	
	public boolean sendMail(String to,String subject, String body)
	{
		MimeMessage mmessage=mailsender.createMimeMessage();
		
		MimeMessageHelper helper=new MimeMessageHelper(mmessage);
		boolean issent=false;
							try {
								helper.setTo(to);
								helper.setSubject(subject);
								helper.setText(body, true);
								
								mailsender.send(mmessage);
								issent=true;
							} catch (MessagingException e) {
								
								e.printStackTrace();
							}
							
		
		return issent;
	}
}

