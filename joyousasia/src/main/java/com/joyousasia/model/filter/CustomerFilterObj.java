package com.joyousasia.model.filter;

import java.io.Serializable;


public class CustomerFilterObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2813838070610914042L;
	
	private String identificationNum;
	
	private String name;
	
	private String contactNum;
	
	private String email;
	
	private String invoiceNum;
	
	private String event;
	
	/* Constructor */
	public CustomerFilterObj(){
		
	}

	public CustomerFilterObj(String identificationNum, String name,
			String contactNum, String email, String invoiceNum, String event) {
		super();
		this.identificationNum = identificationNum;
		this.name = name;
		this.contactNum = contactNum;
		this.email = email;
		this.invoiceNum = invoiceNum;
		this.event = event;
	}

	/* Getter and Setter */
	{}

	public String getIdentificationNum() {
		return identificationNum;
	}

	public void setIdentificationNum(String identificationNum) {
		this.identificationNum = identificationNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	

}
