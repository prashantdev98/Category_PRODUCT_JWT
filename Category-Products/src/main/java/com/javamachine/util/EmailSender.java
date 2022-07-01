package com.javamachine.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import com.javamachine.dto.EmailSenderDto;
import com.javamachine.entity.Product;
@Service
public class EmailSender{

	 @Autowired 
	 private JavaMailSender javaMailSender;
	 
	    @Value("${spring.mail.username}") private String sender;
	
	  public String sendSimpleMail(EmailSenderDto emailSenderDto)
	    {
	 
	        // Try block to check for exceptions
	        try {
	 
	        	SimpleMailMessage mailMessage = new SimpleMailMessage();
	            // Creating a simple mail message
//	            EmailSenderDto mailMessage = new EmailSenderDto();
	 
//	            mailMessage.setEmailSender(null);
//	            mailMessage.setEmailSubject(null);
//	            mailMessage.setOrderList(null);
//	 			mail
//	        	List<String> list = Arrays.asList("A", "B", "C");
//	            String delim = "-";
	     
//	            String res = String.join(delim, emailSenderDto.getOrderList());
	        	StringBuilder proList = new StringBuilder();
	        	for (Product product : emailSenderDto.getOrderList()) {
					proList.append("[ID: "+product.getProductId()+" PRODUCTNAME: "+product.getProductName()+" ]");
					proList.append("\n");
				} 
	        	 mailMessage.setFrom(sender);
	             mailMessage.setTo(emailSenderDto.getEmailReceiver());
	             mailMessage.setText(proList.toString());
	             mailMessage.setSubject(emailSenderDto.getEmailSubject());
	     
	            javaMailSender.send(mailMessage);
	            return "Mail Sent Successfully...";
        	  } catch (Exception e) {
                  return "Error while Sending Mail";
              }
	    }

}