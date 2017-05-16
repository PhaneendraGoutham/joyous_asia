package com.joyousasia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="customer"/*, indexes = {
			 @Index(name="IDX_ID_NUM", columnList="identification_num"),
			 @Index(name="IDX_SEARCH_FILTER", columnList="identification_num, name, invoice_num, email, contact_num, event")
			}*/
		)
public class CustomerDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4713338087915605070L;

	
	/* Columns Configuration */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customer_id", columnDefinition = "bigint", unique = true, nullable = false)
	private Long customerId;
	
	@Column(name = "identification_num", columnDefinition = "varchar(100)", unique = true)
	private String identificationNum;
	
	@Column(name = "invoice_num", columnDefinition = "varchar(50)")
	private String invoiceNum;
	
	@Column(name = "school", columnDefinition = "varchar(150)")
	private String school;
	
	@Column(name = "name", columnDefinition = "varchar(150)")
	private String name;
	
	@Column(name = "faculty", columnDefinition="varchar(150)")
	private String faculty;
	
	@Column(name = "contact_num", columnDefinition="varchar(50)")
	private String contactNum;
	
	@Column(name = "event", columnDefinition = "varchar(255)")
	private String event;
	
	@Column(name = "address", columnDefinition = "varchar(255)")
	private String address;
	
	@Column(name = "email", columnDefinition = "varchar(150)")
	private String email;
	
	@Column(name = "height", columnDefinition = "int default 0")
	private int height;
	
	@Column(name = "weight", columnDefinition = "int default 0")
	private int weight;
	
	@Column(name = "size", columnDefinition = "varchar(50)")
	private String size;
	
	@Column(name = "rental", columnDefinition = "int default 0")
	private int rental;
	
	@Column(name = "deposit", columnDefinition = "int default 0")
	private int deposit;
	
	@Column(name = "return_gown_fromtime_totime", columnDefinition = "varchar(100)")
	private String returnGownFromtimeTotime;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name = "rental_from", columnDefinition="date")
	private Date rentalFrom;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(name = "rental_to", columnDefinition="date")
	private Date rentalTo;
	
	@Column(name = "rental_venue", columnDefinition = "varchar(255)")
	private String rentalVenue;
	
	@Column(name = "rental_return_details", columnDefinition = "int default 0")
	private int rentalReturnDetails;
	
	@Column(name = "js_receipt_num", columnDefinition="varchar(50)")
	private String jsReceiptNum;
	
	@Column(name = "transfer_amount", columnDefinition="double default 0.0")
	private double transferAmount;
	
	@Column(name = "pay_by_detail", columnDefinition = "int default 0")
	private int payByDetail;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "rental_collect_date", columnDefinition="datetime")
	private Date rentalCollectDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "rental_return_date", columnDefinition="datetime")
	private Date rentalReturnDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "deposit_return_date", columnDefinition="datetime")
	private Date depositReturnDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date", columnDefinition="datetime")
	private Date createdDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
	@Column(name = "last_updated", columnDefinition="datetime")
	private Date lastUpdated;
	
	@Column(name = "edit_counter", columnDefinition = "int default 0")
	private int editCounter;
	
	@Column(name = "remarks", columnDefinition="text")
	private String remarks;
	
	@Transient
	private String itemQuantityString;
	
	
	
	/* Constructor (You can create an constructor for default value setting) */
	public CustomerDTO(){
		
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "CustomerDTO ["
				+ "customer_id=" + customerId + ", "
				+ "identification_num=" + identificationNum + ", "
				+ "invoice_num=" + invoiceNum + ", "
				+ "school=" + school + ", "
				+ "name=" + name + ", "
				+ "faculty=" + faculty + ", "
				+ "event=" + event + ","
				+ "contact_num=" + contactNum + ", "
				+ "address=" + address + ", "
				+ "email=" + email + ", "
				+ "height=" + height + ", "
				+ "weight=" + weight + ", "
				+ "size=" + size + ", "
				+ "rental=" + rental + ", "
				+ "deposit=" + deposit + ", "
				+ "return_gown_fromtime_totime" + returnGownFromtimeTotime + ", "
				+ "rental_from=" + rentalFrom + ", "
				+ "rental_to=" + rentalTo + ", "
				+ "rental_venue=" + rentalVenue + ", "
				+ "rental_return_details=" + rentalReturnDetails + ", "
				+ "rental_collect_date=" + rentalCollectDate + ", "
				+ "rental_return_date=" + rentalReturnDate + ", "
				+ "deposit_return_date=" + depositReturnDate + ", "
				+ "js_receipt_num=" + jsReceiptNum + ", "
				+ "transfer_amount=" + transferAmount + ", "
				+ "pay_by_detail=" + payByDetail + ", "
				+ "created_date=" + createdDate + ", "
				+ "last_updated=" + lastUpdated + ", "
				+ "edit_counter=" + editCounter + ", "
				+ "remarks=" + remarks
				+"]";
	}
	
	
	/* Getter and Setter */
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getIdentificationNum() {
		return identificationNum;
	}


	public void setIdentificationNum(String identificationNum) {
		this.identificationNum = identificationNum;
	}
	

	public String getInvoiceNum() {
		return invoiceNum;
	}


	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFaculty() {
		return faculty;
	}


	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}


	public String getContactNum() {
		return contactNum;
	}


	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public int getRental() {
		return rental;
	}


	public void setRental(int rental) {
		this.rental = rental;
	}


	public int getDeposit() {
		return deposit;
	}


	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	

	public String getReturnGownFromtimeTotime() {
		return returnGownFromtimeTotime;
	}


	public void setReturnGownFromtimeTotime(String returnGownFromtimeTotime) {
		this.returnGownFromtimeTotime = returnGownFromtimeTotime;
	}


	public Date getRentalFrom() {
		return rentalFrom;
	}


	public void setRentalFrom(Date rentalFrom) {
		this.rentalFrom = rentalFrom;
	}


	public Date getRentalTo() {
		return rentalTo;
	}


	public void setRentalTo(Date rentalTo) {
		this.rentalTo = rentalTo;
	}


	public String getRentalVenue() {
		return rentalVenue;
	}


	public void setRentalVenue(String rentalVenue) {
		this.rentalVenue = rentalVenue;
	}


	public int getRentalReturnDetails() {
		return rentalReturnDetails;
	}


	public void setRentalReturnDetails(int rentalReturnDetails) {
		this.rentalReturnDetails = rentalReturnDetails;
	}


	public String getJsReceiptNum() {
		return jsReceiptNum;
	}


	public void setJsReceiptNum(String jsReceiptNum) {
		this.jsReceiptNum = jsReceiptNum;
	}
	
	
	public double getTransferAmount() {
		return transferAmount;
	}


	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}


	public int getPayByDetail() {
		return payByDetail;
	}


	public void setPayByDetail(int payByDetail) {
		this.payByDetail = payByDetail;
	}


	public Date getRentalCollectDate() {
		return rentalCollectDate;
	}


	public void setRentalCollectDate(Date rentalCollectDate) {
		this.rentalCollectDate = rentalCollectDate;
	}


	public Date getRentalReturnDate() {
		return rentalReturnDate;
	}


	public void setRentalReturnDate(Date rentalReturnDate) {
		this.rentalReturnDate = rentalReturnDate;
	}


	public Date getDepositReturnDate() {
		return depositReturnDate;
	}


	public void setDepositReturnDate(Date depositReturnDate) {
		this.depositReturnDate = depositReturnDate;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	

	public int getEditCounter() {
		return editCounter;
	}


	public void setEditCounter(int editCounter) {
		this.editCounter = editCounter;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public String getItemQuantityString() {
		return itemQuantityString;
	}


	public void setItemQuantityString(String itemQuantityString) {
		this.itemQuantityString = itemQuantityString;
	}

	
}
