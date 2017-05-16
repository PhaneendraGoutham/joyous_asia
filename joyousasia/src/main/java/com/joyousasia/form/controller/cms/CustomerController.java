package com.joyousasia.form.controller.cms;

/**
 * Author: Vincent
 */


import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joyousasia.common.AjaxListResponse;
import com.joyousasia.common.CSVFileHandler;
import com.joyousasia.dao.cms.CustomerDao;
import com.joyousasia.form.controller.BaseController;
import com.joyousasia.model.CustomerDTO;
import com.joyousasia.model.filter.CustomerFilterObj;
import com.joyousasia.model.filter.DataTableParameterObj;
import com.joyousasia.service.cms.CustomerServiceImpl;
import com.joyousasia.service.cms.EventServiceImpl;
import com.joyousasia.service.cms.FacultyServiceImpl;
import com.joyousasia.service.cms.InvoiceReceiptServiceImpl;
import com.joyousasia.service.cms.SchoolServiceImpl;
import com.joyousasia.service.cms.TransactionServiceImpl;
import com.joyousasia.util.ConnConfig;


@Controller
public class CustomerController extends BaseController
{
	private static final Logger log = Logger.getLogger(CustomerController.class);
	
	private static final boolean ADD_TRANSACTION = false;
	private static final boolean UPDATE_TRANSACTION = true;
	public static final String DEST = ConnConfig.getWebAppPath()+"\\sandbox\\invoice_reciept";
	public static final String PURGE_DIR_DEST = ConnConfig.getWebAppPath()+"\\sandbox";
	public static final String INVOICE_FORMAT = ".pdf";
	public static final String SAVE_AND_PRINT = "1";
	
	@Autowired  
	private MessageSource messageSource;
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Autowired  
	private TransactionServiceImpl transactionService;
	
	@Autowired  
	private InvoiceReceiptServiceImpl invoiceRecieptService;
	
	@Autowired  
	private EventServiceImpl eventService;
	
	@Autowired  
	private SchoolServiceImpl schoolService;
	
	@Autowired  
	private FacultyServiceImpl facultyService;
	
	@Autowired  
	private CustomerDao customerDao;
	
	@Override
	protected String getModuleFolder() {
		return "customer/";
	}

	@RequestMapping(value = { "customer/index" + SPRING_EXT, "customer/list" + SPRING_EXT })
	public String index(
			Model model
			) {
		
		log.debug("| customer/list | entry");
		model.addAttribute("eventList", eventService.getAllEvents());
		log.debug("| customer/list | exit");
		return goToPageJsp("list");

	}
	
	
	@ResponseBody
	@RequestMapping(value = "customer/ajax_empty_list" + SPRING_EXT, headers = "Accept=application/json")
	public AjaxListResponse ajax_empty_list(
			String sEcho, 
			int iDisplayStart, 
			int iDisplayLength,
			int iSortCol_0,
			String sSortDir_0,
			String sSearch,
			HttpServletRequest req
			) 
	{

		log.debug("| customer/ajax_empty_list | entry");
		
		AjaxListResponse resp = new AjaxListResponse();
		resp.setsEcho(sEcho);
		resp.setiTotalRecords((long)0);
		resp.setiTotalDisplayRecords((long)0);
		List<Map<String, Object>> aaList = new LinkedList<Map<String, Object>>();
		resp.setAaData(aaList);
		log.info("| listCustomer() | NO PARAMETER | empty list.");
		log.debug("| customer/ajax_empty_list | exit");
		return resp;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "customer/ajax_list" + SPRING_EXT, headers = "Accept=application/json")
	public AjaxListResponse ajax_customer_list(
			String sEcho, 
			int iDisplayStart, 
			int iDisplayLength,
			int iSortCol_0,
			String sSortDir_0,
			String sSearch,
			HttpServletRequest req
			) 
	{

		log.debug("| customer/ajax_customer_list | entry");
		
		
		CustomerFilterObj filter = new CustomerFilterObj(req.getParameter("identificationNum"),  
				req.getParameter("name"), req.getParameter("contactNum"), req.getParameter("email"),
				req.getParameter("invoiceNum"), req.getParameter("event"));

		DataTableParameterObj dataTableParameters = new DataTableParameterObj(sEcho, iDisplayStart, 
				iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
		
		AjaxListResponse resp = customerService.getCustomerAjaxListResponse(filter, dataTableParameters);
		
		log.info("| listCustomer() | NO PARAMETER | List successfully.");
		log.debug("| customer/ajax_customer_list | exit");
		return resp;

	}
	

	@RequestMapping(value = "customer/add" + SPRING_EXT, method = RequestMethod.GET)
	public String add_customer(
			Model model
			) 
	{
		
		log.debug("| CustomerController | customer/add | GET | entry");
		
		model.addAttribute("CustomerDTO", new CustomerDTO()); // adding in model
		String jsonString = "";
		try {
			jsonString = customerService.getItemListJsonString();
		} catch (JsonProcessingException e) {
			model.addAttribute("errmsg", "Json Processing Failed!");
		}
		model.addAttribute("itemList", jsonString);
		model.addAttribute("eventList", eventService.getAllActiveEvents());
		model.addAttribute("schoolList", schoolService.getAllActiveSchools());
		model.addAttribute("facultyList", facultyService.getAllActiveFacultys());
		
		log.debug("| CustomerController | customer/add | GET | exit");
		return goToPageJsp("add");
            
	}
	
	@RequestMapping(value = "customer/add" + SPRING_EXT, method = RequestMethod.POST)
	public String customer_add_submit(
			Model model,
			@ModelAttribute("CustomerDTO") CustomerDTO customerDTO,
			HttpServletRequest req
			) 
	{
		log.debug("| CustomerController | customer/add | POST | entry");
		try {
			log.debug("| CustomerController | customer/add | POST | Identification No.: "+ customerDTO.getIdentificationNum());
			log.debug("| CustomerController | customer/add | POST | Customer Name: "+ customerDTO.getName());
			log.debug("| CustomerController | customer/add | POST | Item Quantity String: "+ customerDTO.getItemQuantityString());
			
			customerService.createCustomer(customerDTO);
			log.debug("| CustomerController | customer/add | POST | Inserted into Database, Customer ID: "+ customerDTO.getCustomerId());
			if(!StringUtil.isBlank(customerDTO.getItemQuantityString())) {
				transactionService.saveTransactions(customerDTO.getCustomerId(), customerDTO.getItemQuantityString(), ADD_TRANSACTION);
				log.debug("| CustomerController | customer/add | Save transaction Successful.");
			}
			
			if(req.getParameter("isPrint").equals(SAVE_AND_PRINT)) {
				String PDF_PATH = DEST+"_"+getCurrentUnixTimestamp()+INVOICE_FORMAT;
				invoiceRecieptService.createPdf(PDF_PATH, customerDTO);
				invoiceRecieptService.printPdf(PDF_PATH);
			}
			
			log.debug("| CustomerController | customer/add | POST | exit");
			model.addAttribute("errmsg", "Add Successul!");
			model.addAttribute("eventList", eventService.getAllEvents());
	        return goToPageJsp("list");
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| CustomerController | customer/add | POST |New Customer|Add failed.");
			model.addAttribute("errmsg", "Add failed!");
			model.addAttribute("eventList", eventService.getAllEvents());
			return add_customer( model);
		}
        
	}
	
	@RequestMapping(value = "customer/edit" + SPRING_EXT, method = RequestMethod.GET)
	public String customer_edit(
			Model model
			, HttpServletRequest req
			, @RequestParam(required = true) Long id
			) 
	{
		
		log.debug("| CustomerController | customer/edit | GET | entry");
		
		CustomerDTO customerDTO = customerService.findByCustomerId(id);
		model.addAttribute("CustomerDTO", customerDTO); // adding in model
		String jsonString = "";
		try {
			jsonString = customerService.getItemListJsonStringByCustomerId(id);
			/*if didn't buy any item before, use current item list*/
			if(jsonString.equals("[]")) {
				jsonString = customerService.getItemListJsonString();
			}
		} catch (JsonProcessingException e) {
			model.addAttribute("errmsg", "Json Processing Failed!");
		}
		model.addAttribute("itemList", jsonString);
		model.addAttribute("eventList", eventService.getAllEvents());
		model.addAttribute("schoolList", schoolService.getAllSchools());
		model.addAttribute("facultyList", facultyService.getAllFacultys());
		
		log.debug("| CustomerController | organization/edit | GET | exit");
		return goToPageJsp("edit");
        
	}
	
	@RequestMapping(value = "customer/edit" + SPRING_EXT, method = RequestMethod.POST)
	public String customer_edit_submit(
			Model model,
			@ModelAttribute("CustomerDTO") CustomerDTO customerDTO,
			HttpServletRequest req
			)
	{
		
		log.debug("| CustomerController | customer/edit | POST | entry");
		
		try {
			
			log.debug("Trying to edit customer which customer_id = " + customerDTO.getCustomerId());
			log.debug("| CustomerController | customer/edit | POST | ItemQuantityString: " + customerDTO.getItemQuantityString());
			
			customerService.editCustomer(customerDTO);
			log.debug("| CustomerController | customer/edit | POST | Database Updated");
			if(!StringUtil.isBlank(customerDTO.getItemQuantityString())) {
				transactionService.saveTransactions(customerDTO.getCustomerId(), customerDTO.getItemQuantityString(), UPDATE_TRANSACTION);
				log.debug("| CustomerController | customer/edit | Save transaction Successful.");
			}
			
			if(req.getParameter("isPrint").equals(SAVE_AND_PRINT)) {
				String PDF_PATH = DEST+"_"+getCurrentUnixTimestamp()+INVOICE_FORMAT;
				invoiceRecieptService.createPdf(PDF_PATH, customerDTO);
//				invoiceRecieptService.printPdf(PDF_PATH);
			}
			
			
			log.debug("| CustomerController | customer/edit | POST | exit");
			model.addAttribute("errmsg", "Edit Successful!");
			model.addAttribute("eventList", eventService.getAllEvents());
			return goToPageJsp("list");
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| CustomerController | customer/edit | POST | customer_id = "+customerDTO.getCustomerId()+ "|Edit failed.");
			model.addAttribute("errmsg", "Edit failed!");
			return customer_edit(model, 
					req, 
					customerDTO.getCustomerId()
					);
		}
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "customer/delete" + SPRING_EXT, method = RequestMethod.POST)
	public String delete_customer(
			Model model
			, @RequestParam("id") String idList
			)
	{
		
		log.debug("| CustomerController | customer/delete | POST | entry");
		
		try {
			
			customerService.deleteCustomerByIdList(idList);
			log.info("| CustomerController | customer/delete | Delete Successful.");
			log.debug("| CustomerController | customer/delete | POST | exit");
			return "Delete Successful!";
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| CustomerController | customer/delete | Delete failed.");
			return "Delete failed!";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "customer/purge_database" + SPRING_EXT, headers = "Accept=application/json")
	public String purge_customer_records()
	{
		
		log.debug("| CustomerController | customer/purge_database | GET | entry");
		String errorMsg = null;
		try {
			
			customerService.purgeCustomerRecords();
			log.debug("| CustomerController | customer/purge_database | GET | Customer records were purged");
			log.debug("| CustomerController | customer/purge_database | GET | exit");
			errorMsg = "Purge successful!";
			return errorMsg;
			
		} catch (Exception e) {
			
			log.info(e,e);
			log.info("| CustomerController | customer/purge_database | GET | Purge failed.");
			log.debug("| CustomerController | customer/purge_database | GET | exit");
			errorMsg = "Purge failed!";
			return errorMsg;
			
		}
		
		
	}
	
	/*
	 * function: customer_download
	 * Trigger when user click download button
	 */
	@RequestMapping(value = "customer/download" + SPRING_EXT, method = RequestMethod.POST)
	public void customer_download(
			HttpServletRequest req,
			HttpServletResponse response
			) 
	{
		
		log.debug("| customer_download | Entry");
		
		/*Configuration*/
		String current_datetime = SDF_YYYY_MM_DD.format(new Date());
		String filename = "STUDENTLIST_"+current_datetime+".csv";
		String[] header = customerService.setUpCSVHeader();
		final CellProcessor[] whiterProcessors = customerService.setUpWriterProcessors(header);
		/*Configuration*/
		
		/*Data*/
		CustomerFilterObj filter = new CustomerFilterObj(req.getParameter("identificationNum"),  
				req.getParameter("name"), req.getParameter("contactNum"), req.getParameter("email"),
				req.getParameter("invoiceNum"), req.getParameter("event"));
		List<CustomerDTO> customerList = customerService.getDownloadListByFilter(filter);
		List<Map<String, Object>> customerMap = customerService.convertCustomerListToMapList(header, customerList);
		/*Data*/
		
		CSVFileHandler handler = new CSVFileHandler();
		try {
			
			handler.downloadCustomerCSV(customerMap, header, whiterProcessors, response, filename);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
			
		log.debug("| customer_download | Exit");
            
	}
	
	@ResponseBody
	@RequestMapping(value = "customer/get_latest_return_date_by_event" + SPRING_EXT, method = RequestMethod.POST)
	public String get_latest_return_date_by_event(
			@RequestParam("eventName") String eventName
			)
	{
		
		log.debug("| CustomerController | customer/get_latest_return_date_by_event | POST | entry");
		
		try {
			
			String latestReturnDate = eventService.getLatestReturnDateByEventName(eventName);
			log.debug("| CustomerController | customer/get_latest_return_date_by_event | POST | exit");
			return latestReturnDate;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| CustomerController | customer/get_latest_return_date_by_event | Delete failed.");
			return "failed";
		}
		
	}
	
	@RequestMapping(value = "customer/test" + SPRING_EXT, method = RequestMethod.GET)
	public String test(
			) 
	{
		
		log.debug("| CustomerController | customer/add | GET | entry");
		
		for(int i=0; i< 10000; i++) {
			CustomerDTO customer = new CustomerDTO();
			customer.setIdentificationNum("V"+i+"G");
			customer.setAddress("Jurong East Ave 1 Blk 332 #06-1770 Singapore 600332");
			customer.setContactNum("94567465");
			customer.setCreatedDate(new Date());
			customer.setDeposit(150);
			customer.setEmail("vincentgeng@gmail.com");
			customer.setEvent("JCU Singapore Graduation 2016");
			customer.setFaculty("Master of IT");
			customer.setHeight(175);
			customer.setInvoiceNum("VG"+i);
			customer.setLastUpdated(new Date());
			customer.setName("Vincent Geng");
			customer.setRental(48);
			customer.setRentalFrom(new Date());
			customer.setRentalTo(new Date());
			customer.setRentalVenue("Marina Bay Sand");
			customer.setReturnGownFromtimeTotime("02:00 PM-07:30 PM");
			customer.setSchool("JCU Singapore");
			customer.setSize("M");
			customer.setWeight(60);
			customerDao.save(customer);
		}
		
		log.debug("| CustomerController | customer/add | GET | exit");
		return goToPageJsp("list");
            
	}

	
}
	