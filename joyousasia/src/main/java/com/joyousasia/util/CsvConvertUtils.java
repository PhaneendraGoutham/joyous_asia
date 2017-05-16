package com.joyousasia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.joyousasia.model.csv.FromToDateObject;
import com.joyousasia.model.csv.ReturnDetailObject;

public class CsvConvertUtils {
	
	public static FromToDateObject extractDateFromCollectReturnDetail(String collectDetail, String returnDetail) {
		
		String[] collectDetailArray = collectDetail.split(",");
		String[] returnDetailArray = returnDetail.split(",");
		
		Date fromDate = null;
		Date toDate = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	    
		try {
			
			fromDate = dateFormat.parse(collectDetailArray[0]);
			toDate = dateFormat.parse(returnDetailArray[0]);
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String returnVenue = "";
		for(int i=2; i<returnDetailArray.length; i++) {
			if(i==2) {
				returnVenue += returnDetailArray[i].trim();
			}else {
				returnVenue += ","+returnDetailArray[i];
			}
		}
		
		FromToDateObject fromToDateObject = new FromToDateObject(fromDate, toDate, returnVenue);
		return fromToDateObject;
	}
	
	public ReturnDetailObject convertDetailToObject(String detail) {
		
		String[] detailArray = detail.split(",");
		
		Date fromTime = null;
		Date toTime = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
	    
		/*04-Jan-2015*/
		System.out.println(detailArray[0]);
				
		/*from 10:00 AM to 05:00 PM*/
		String[] time = detailArray[1].split("from|to");
		System.out.println("From Time:"+time[1]);
		System.out.println("To Time:"+time[2]);
		try {
			fromTime = dateFormat.parse(detailArray[0]+time[1]);
			toTime = dateFormat.parse(detailArray[0]+time[2]);
			String fromTimeString = dateFormat.format(fromTime);
	        String toTimeString = dateFormat.format(toTime);
	        System.out.println("From Time String:"+fromTimeString);
	        System.out.println("To Time String:"+toTimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*No 1 Sims Lane, #04-04, One Sims Lane*/
		String rentalVenue = "";
		for(int i=2; i<detailArray.length; i++) {
			if(i==2) {
				rentalVenue += detailArray[i].trim();
			}else {
				rentalVenue += ","+detailArray[i];
			}
		}
		System.out.println(rentalVenue);
		
		ReturnDetailObject returnDetailObject = new ReturnDetailObject(fromTime, toTime, rentalVenue);
		
		return returnDetailObject;
	}
	
	public static String getReturnGownFromTimeToTimeString(String detail) {
		
		String[] detailArray = detail.split(",");
		String[] time = detailArray[1].split("from|to");
		String returnGownFromTimeToTime = time[1].trim()+"-"+time[2].trim();
		return returnGownFromTimeToTime;
		
	}
}
