package com.joyousasia.model.csv;


public class CustomerInputEndWithEmptyColumnObject extends CustomerInputObject{
	
	
	/* Columns */
	private String emptyColumn;
	
	/* Constructor (You can create an constructor for default value setting) */
	public CustomerInputEndWithEmptyColumnObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* toString() Function */
	@Override
	public String toString() {
		return "CustomerInputEndWithEmptyColumnObject ["
				+ "customerId=" + customerId + ", "
				+ "status=" + status + ", "
				+ "name=" + name + ", "
				+ "identificationNum=" + identificationNum + ", "
				+ "preferredNameOnCertificate1=" + preferredNameOnCertificate1 + ", "
				+ "preferredNameOnCertificate2=" + preferredNameOnCertificate2 + ", "
				+ "PreferredNameCalledOnStage1=" + PreferredNameCalledOnStage1 + ", "
				+ "PreferredNameCalledOnStage2=" + PreferredNameCalledOnStage2 + ", "
				+ "parentName=" + parentName + ", "
				+ "clazz=" + clazz + ", "
				+ "address=" + address + ", "
				+ "contactNum=" + contactNum + ", "
				+ "email=" + email + ", "
				+ "event=" + event + ", "
				+ "school=" + school + ", "
				+ "schoolBranch=" + schoolBranch + ", "
				+ "program=" + program + ", "
				+ "regaliaRental=" + regaliaRental + ", "
				+ "regaliaDeposit=" + regaliaDeposit + ", "
				+ "regaliaTotal=" + regaliaTotal + ", "
				+ "regaliaRental2=" + regaliaRental2 + ", "
				+ "regaliaDeposit2=" + regaliaDeposit2 + ", "
				+ "regaliaTotal2=" + regaliaTotal2 + ", "
				+ "height=" + height + ", "
				+ "weight=" + weight + ", "
				+ "headcircum=" + headcircum + ", "
				+ "regaliaCollectDetails=" + regaliaCollectDetails + ", "
				+ "regaliaReturnDetails=" + regaliaReturnDetails + ", "
				+ "regaliaPaymentStatus=" + regaliaPaymentStatus + ", "
				+ "ticketActive=" + ticketActive + ", "
				+ "ticketGraduate=" + ticketGraduate + ", "
				+ "ticketGuestComplimentary=" + ticketGuestComplimentary + ", "
				+ "ticketGuest=" + ticketGuest + ", "
				+ "ticketChildComplimentary=" + ticketChildComplimentary + ", "
				+ "ticketChild=" + ticketChild + ", "
				+ "ticketTotal=" + ticketTotal + ", "
				+ "specialRequest=" + specialRequest + ", "
				+ "ticketPaymentType=" + ticketPaymentType + ", "
				+ "ticketTransId=" + ticketTransId + ", "
				+ "ticketPaymentStatus=" + ticketPaymentStatus + ", "
				+ "activityName=" + activityName + ", "
				+ "activityVenue=" + activityVenue + ", "
				+ "activityDate=" + activityDate + ", "
				+ "activityTime=" + activityTime + ", "
				+ "reportingTime=" + reportingTime + ", "
				+ "activityTicketPrice=" + activityTicketPrice + ", "
				+ "activityPrice=" + activityPrice + ", "
				+ "guestTicket=" + guestTicket + ", "
				+ "active=" + active
				+"]";
	}
	


	/* Getter and Setter */
	public String getEmptyColumn() {
		return emptyColumn;
	}


	public void setEmptyColumn(String emptyColumn) {
		this.emptyColumn = emptyColumn;
	}
	
	
}
