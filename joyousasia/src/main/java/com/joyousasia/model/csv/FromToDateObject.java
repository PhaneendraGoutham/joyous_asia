package com.joyousasia.model.csv;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FromToDateObject {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
	/* Attributes */
	private Date fromDate;
	private Date toDate;
	private String returnVenue;
	
	/* Constructor (You can create an constructor for default value setting) */
	public FromToDateObject(){
		
	}
	
	public FromToDateObject(Date fromDate, Date toDate, String returnVenue){
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.returnVenue = returnVenue;
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "FromToDateObject ["
				+ "fromDate=" + dateFormat.format(fromDate) + ", "
				+ "toDate=" + dateFormat.format(toDate) + ", "
				+ "returnVenue=" + returnVenue
				+"]";
	}

	
	/* Getter and Setter */
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getReturnVenue() {
		return returnVenue;
	}

	public void setReturnVenue(String returnVenue) {
		this.returnVenue = returnVenue;
	}
	
}
