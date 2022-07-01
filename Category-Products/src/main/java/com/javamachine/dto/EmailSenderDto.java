package com.javamachine.dto;

import java.util.List;

import com.javamachine.entity.Product;

public class EmailSenderDto {

	private String emailSender;
	
	private String emailReceiver;
	
	 private String emailSubject;
	
	private List<Product> orderList;
	
	
	public EmailSenderDto() {
		
	}
	
	
	

	public EmailSenderDto(String emailSender, String emailReceiver, String emailSubject, List<Product> orderList) {
		super();
		this.emailSender = emailSender;
		this.emailReceiver = emailReceiver;
		this.emailSubject = emailSubject;
		this.orderList = orderList;
	}




	public String getEmailSender() {
		return emailSender;
	}


	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}


	public String getEmailSubject() {
		return emailSubject;
	}


	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}


	public List<Product> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Product> orderList) {
		this.orderList = orderList;
	}




	public String getEmailReceiver() {
		return emailReceiver;
	}




	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}
	
	
	
}
