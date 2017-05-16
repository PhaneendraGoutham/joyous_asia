package com.joyousasia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="invoice_reciept")
public class InvoiceReceiptDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6659321078476231041L;

	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "invoice_id", columnDefinition = "int", unique = true, nullable = false)
	private Integer invoiceId;
	
	@Column(name = "logo", columnDefinition = "varchar(255)", nullable = false)
	private String logo;
	
	@Column(name = "address", columnDefinition = "varchar(255)", nullable = false)
	private String address;
	
	@Column(name = "tel", columnDefinition = "varchar(50)", nullable = false)
	private String tel;
	
	@Column(name = "website", columnDefinition="varchar(60)", nullable=false)
	private String website;
	
	@Column(name = "email", columnDefinition="varchar(60)", nullable=false)
	private String email;
	
	@Column(name = "terms_and_conditions", columnDefinition="text", nullable=false)
	private String termsAndConditions;
	
	
	/* Constructor (You can create an constructor for default value setting) */
	public InvoiceReceiptDTO(){
		
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "InvoiceRecieptDTO ["
				+ "invoice_id=" + invoiceId + ", "
				+ "logo=" + logo + ", "
				+ "address=" + address + ", "
				+ "tel=" + tel + ", "
				+ "website=" + website + ", "
				+ "email=" + email + ", "
				+ "terms_and_conditions=" + termsAndConditions + ", "
				+"]";
	}
	
	
	/* Getter and Setter */
	public Integer getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTermsAndConditions() {
		return termsAndConditions;
	}


	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	
}
