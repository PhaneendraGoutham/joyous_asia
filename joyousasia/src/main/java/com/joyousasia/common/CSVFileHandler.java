package com.joyousasia.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import com.joyousasia.dao.cms.CustomerDao;
import com.joyousasia.dao.cms.EventDao;
import com.joyousasia.dao.cms.FacultyDao;
import com.joyousasia.dao.cms.SchoolDao;
import com.joyousasia.model.CustomerDTO;
import com.joyousasia.model.EventDTO;
import com.joyousasia.model.FacultyDTO;
import com.joyousasia.model.SchoolDTO;
import com.joyousasia.model.csv.CustomerInputEndWithEmptyColumnObject;
import com.joyousasia.model.csv.CustomerInputObject;
import com.joyousasia.model.csv.FromToDateObject;
import com.joyousasia.service.cms.BaseService;
import com.joyousasia.util.CsvConvertUtils;

@Component
public class CSVFileHandler extends BaseService{

	private static final Logger log = Logger.getLogger(CSVFileHandler.class);
//	private static final Integer CUSTOMER_TOTAL_COLUMN = 52;
	private static final Integer EVENT_INACTIVE = 0;
	private static final Integer SCHOOL_INACTIVE = 0;
	private static final Integer FACULTY_INACTIVE = 0;
	
	@Autowired  
	private CustomerDao customerDao;
	
	@Autowired  
	private EventDao eventDao;
	
	@Autowired  
	private SchoolDao schoolDao;
	
	@Autowired  
	private FacultyDao facultyDao;
	
	//Reading with CsvBeanReader
	public String handleCustomer (
			File file
			) throws Exception{
		
		ICsvBeanReader beanReader = null;
		CustomerInputObject instance;
		
        StringBuilder finalErrorBuilder = new StringBuilder();
        
		try {
			
			log.info("| handleCustomer | entry");
			beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
			String[] checkHearder = beanReader.getHeader(true);
			log.info("| handleCustomer | header length: "+ checkHearder.length);
			final String[] headerWithoutEmptyColumn = {
					"customerId",
					"registerDate",
					"status",
					"name",
					"identificationNum",
					"preferredNameOnCertificate1",
					"preferredNameOnCertificate2",
					"PreferredNameCalledOnStage1",
					"PreferredNameCalledOnStage2",
					"parentName",
					"clazz",
					"address",
					"contactNum",
					"email",
					"event",
					"school",
					"schoolBranch",
					"program",
					"regaliaRental",
					"regaliaDeposit",
					"regaliaTotal",
					"regaliaRental2",
					"regaliaDeposit2",
					"regaliaTotal2",
					"height",
					"weight",
					"headcircum",
					"regaliaCollectDetails",
					"regaliaReturnDetails",
					"regaliaPaymentStatus",
					"ticketActive",
					"ticketGraduate",
					"ticketGuestComplimentary",
					"ticketGuest",
					"ticketChildComplimentary",
					"ticketChild",
					"ticketTotal",
					"specialRequest",
					"ticketPaymentType",
					"ticketTransId",
					"ticketPaymentStatus",
					"activityName",
					"activityVenue",
					"activityVenueWebsite",
					"activityDate",
					"activityTime",
					"reportingTime",
					"activityTicketPrice",
					"activityPrice",
					"guestTicket",
					"active"
					};
			
			final String[] headerEndWithEmptyColumn = {
					"customerId",
					"registerDate",
					"status",
					"name",
					"identificationNum",
					"preferredNameOnCertificate1",
					"preferredNameOnCertificate2",
					"PreferredNameCalledOnStage1",
					"PreferredNameCalledOnStage2",
					"parentName",
					"clazz",
					"address",
					"contactNum",
					"email",
					"event",
					"school",
					"schoolBranch",
					"program",
					"regaliaRental",
					"regaliaDeposit",
					"regaliaTotal",
					"regaliaRental2",
					"regaliaDeposit2",
					"regaliaTotal2",
					"height",
					"weight",
					"headcircum",
					"regaliaCollectDetails",
					"regaliaReturnDetails",
					"regaliaPaymentStatus",
					"ticketActive",
					"ticketGraduate",
					"ticketGuestComplimentary",
					"ticketGuest",
					"ticketChildComplimentary",
					"ticketChild",
					"ticketTotal",
					"specialRequest",
					"ticketPaymentType",
					"ticketTransId",
					"ticketPaymentStatus",
					"activityName",
					"activityVenue",
					"activityVenueWebsite",
					"activityDate",
					"activityTime",
					"reportingTime",
					"activityTicketPrice",
					"activityPrice",
					"guestTicket",
					"active",
					"emptyColumn"
					};
			
			final CellProcessor[] processorsWithoutEmptyColumn = new CellProcessor[]{
					new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional()
		     		};
			
			final CellProcessor[] processorsEndWithEmptyColumn = new CellProcessor[]{
					new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional(),
		        	new Optional()
		     		};
			
			EventDTO tempEventObj = new EventDTO();
			if(checkHearder.length==0) {
				return "CSV file is empty!";
			}else if(checkHearder.length==51) {
				while( (instance = beanReader.read(CustomerInputObject.class, headerWithoutEmptyColumn, processorsWithoutEmptyColumn)) != null ) {
					
					insertCSVDataToDb(instance, finalErrorBuilder, tempEventObj);
					
				}
			}else {
				while( (instance = beanReader.read(CustomerInputEndWithEmptyColumnObject.class, headerEndWithEmptyColumn, processorsEndWithEmptyColumn)) != null ) {
					
					insertCSVDataToDb(instance, finalErrorBuilder, tempEventObj);
					
				}
			}
			List<EventDTO> eventList = eventDao.findByEventName(tempEventObj.getEventName());
			EventDTO event = eventList.get(0);
			event.setGownReturnDate(tempEventObj.getGownReturnDate());
			eventDao.save(event);
			
		} finally {
            if( beanReader != null ) {
            	beanReader.close();
            	log.info("| handleCustomer | exit");
            }
		}
		return finalErrorBuilder.toString();
		
	}

	private void insertCSVDataToDb(CustomerInputObject instance,
			StringBuilder finalErrorBuilder, EventDTO tempEventObj) {
		StringBuilder errorBuilder = new StringBuilder();
		
		if(!StringUtil.isBlank(errorBuilder.toString())) {
			finalErrorBuilder.append(StringUtil.isBlank(finalErrorBuilder.toString()) ? errorBuilder.toString() : "<br>"+errorBuilder.toString());
		}else {
			CustomerDTO customerDTO = new CustomerDTO();
			FromToDateObject fromToDateObject = CsvConvertUtils.extractDateFromCollectReturnDetail(instance.getRegaliaCollectDetails(), instance.getRegaliaReturnDetails());
			String identificationNum = instance.getIdentificationNum();
			customerDTO.setIdentificationNum(identificationNum);
			customerDTO.setSchool(instance.getSchool());
			if(!schoolDao.existsBySchoolName(instance.getSchool())) {
				SchoolDTO school = new SchoolDTO(instance.getSchool(), SCHOOL_INACTIVE, new Date());
				schoolDao.save(school);
			}
			customerDTO.setName(instance.getName());
			customerDTO.setFaculty(instance.getProgram());
			if(!facultyDao.existsByFacultyName(instance.getProgram())&&!StringUtil.isBlank(instance.getProgram())) {
				FacultyDTO faculty = new FacultyDTO(instance.getProgram(), FACULTY_INACTIVE, new Date());
				facultyDao.save(faculty);
			}
			customerDTO.setContactNum(instance.getContactNum());
			
			if(StringUtil.isBlank(tempEventObj.getEventName())) {
				tempEventObj.setEventName(instance.getEvent());
			}
			
			customerDTO.setEvent(instance.getEvent());
			if(!eventDao.existsByEventName(instance.getEvent())) {
				EventDTO event = new EventDTO(instance.getEvent(), EVENT_INACTIVE, new Date());
				eventDao.save(event);
			}
			customerDTO.setAddress(instance.getAddress());
			customerDTO.setEmail(instance.getEmail());
			customerDTO.setHeight(StringUtil.isBlank(instance.getHeight()) ? 0 : convertToInt(instance.getHeight()));
			customerDTO.setWeight(StringUtil.isBlank(instance.getWeight()) ? 0 : convertToInt(instance.getWeight()));
			customerDTO.setSize(StringUtil.isBlank(instance.getHeight()) ? "XS" : heightConvertToSize(instance.getHeight()));
			customerDTO.setRental(StringUtil.isBlank(instance.getRegaliaRental()) ? 0 : convertToInt(instance.getRegaliaRental()));
			customerDTO.setDeposit(StringUtil.isBlank(instance.getRegaliaDeposit()) ? 0 : convertToInt(instance.getRegaliaDeposit()));
			customerDTO.setReturnGownFromtimeTotime(CsvConvertUtils.getReturnGownFromTimeToTimeString(instance.getRegaliaReturnDetails()));
			customerDTO.setRentalFrom(fromToDateObject.getFromDate());
			customerDTO.setRentalTo(fromToDateObject.getToDate());
			if(tempEventObj.getGownReturnDate()==null || tempEventObj.getGownReturnDate().before(fromToDateObject.getToDate())) {
				log.info("| handleCustomer | latest gown return date update: "+ fromToDateObject.getToDate().toString()+", by student who's identification Num: "+instance.getIdentificationNum());
				tempEventObj.setGownReturnDate(fromToDateObject.getToDate());
			}
			customerDTO.setRentalVenue(fromToDateObject.getReturnVenue());
			customerDTO.setCreatedDate(new Date());
			customerDTO.setLastUpdated(new Date());
			if(customerDao.existsByIdentificationNumAndEvent(identificationNum, instance.getEvent())) {
				List<CustomerDTO> deletedCustomerList = customerDao.findByIdentificationNumAndEvent(identificationNum, instance.getEvent());
				for(CustomerDTO c : deletedCustomerList) {
					log.info("| handleCustomer | found a duplicate identification number in database. "+ c.toString());
					log.info("| handleCustomer | customer id ="+c.getCustomerId()+" was deleted.");
					customerDao.delete(c);
				}
			}
			customerDao.save(customerDTO);
			customerDTO.setInvoiceNum(constructInvoiceNumByCustomerId(customerDTO.getCustomerId()));
			customerDao.save(customerDTO);
		}
	}
	
	private String heightConvertToSize(String heightString) {
		Integer height =Integer.valueOf(heightString); 
		String size = "";
		if(height < 160) {
			size = "XS";
		}else if(height >= 160 && height <170) {
			size = "S";
		}else if(height >=170 && height < 180) {
			size = "M";
		}else if(height >=180 && height < 190) {
			size = "L";
		}else if(height >= 190) {
			size = "XL";
		}
		return size;
	}
	
	private int convertToInt(String inputString) {
		String integerString = null;
		if (inputString.contains(".")) {
			String[] inputStringArr = inputString.split("\\.");
			integerString = inputStringArr[0];
		}else {
			if(!StringUtil.isBlank(inputString)) {
				integerString = inputString;
			}
		}
		int result = Integer.valueOf(integerString) == null ? 0 : Integer.valueOf(integerString);
		return result;
	}
	
	public void downloadCustomerCSV(
			List<Map<String, Object>> customerMap,
			String[] header, 
			CellProcessor[] processors, 
			HttpServletResponse response,
			String filename
			) throws IOException {

		response.setContentType("text/csv");
		 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", filename);
        response.setHeader(headerKey, headerValue);
		
		// uses the Super CSV API to generate CSV data from the model data
        ICsvMapWriter csvWriter = new CsvMapWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		csvWriter.writeHeader(header);

		for (Map<String, Object> map : customerMap) {

			 csvWriter.write(map, header, processors);
			 
		}

		csvWriter.close();

	}
}
