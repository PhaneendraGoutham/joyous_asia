package com.joyousasia.model.csv;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ReturnDetailObject {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
	
	/* Attributes */
	private Date fromTime;
	private Date toTime;
	private String rentalVenue;
	
	/* Constructor (You can create an constructor for default value setting) */
	public ReturnDetailObject(){
		
	}
	
	public ReturnDetailObject(Date fromTime, Date toTime, String rentalVenue){
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.rentalVenue = rentalVenue;
	}
	
	
	/* toString() Function */
	@Override
	public String toString() {
		return "ReturnDetailObject ["
				+ "fromDate=" + dateFormat.format(fromTime) + ", "
				+ "toDate=" + dateFormat.format(toTime) + ", "
				+ "rentalVenue=" + rentalVenue
				+"]";
	}

	
	/* Getter and Setter */
	public Date getFromTime() {
		return fromTime;
	}


	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}


	public Date getToTime() {
		return toTime;
	}


	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}


	public String getRentalVenue() {
		return rentalVenue;
	}


	public void setRentalVenue(String rentalVenue) {
		this.rentalVenue = rentalVenue;
	}
	
	
}
