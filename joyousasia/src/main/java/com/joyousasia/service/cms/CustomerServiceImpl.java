package com.joyousasia.service.cms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyousasia.common.AjaxListResponse;
import com.joyousasia.dao.cms.CustomerDao;
import com.joyousasia.dao.cms.CustomerSpecifications;
import com.joyousasia.dao.cms.ItemDao;
import com.joyousasia.dao.cms.TransactionDao;
import com.joyousasia.model.CustomerDTO;
import com.joyousasia.model.ItemDTO;
import com.joyousasia.model.ItemJsonObject;
import com.joyousasia.model.TransactionDTO;
import com.joyousasia.model.filter.CustomerFilterObj;
import com.joyousasia.model.filter.DataTableParameterObj;
import com.joyousasia.util.LongWrapUtils;

@Service
public class CustomerServiceImpl extends BaseService{
	
	private static final Logger log = Logger.getLogger(CustomerServiceImpl.class);
	
	private static final String ORGANIZATION_COLUMN_0 = "customerId";
	private static final String ORGANIZATION_COLUMN_1 = "identificationNum";
	private static final String ORGANIZATION_COLUMN_2 = "invoiceNum";
	private static final String ORGANIZATION_COLUMN_3 = "name";
	private static final String ORGANIZATION_COLUMN_4 = "event";
	private static final String ORGANIZATION_COLUMN_5 = "contactNum";
	private static final String ORGANIZATION_COLUMN_6 = "email";
	
	private static final int ITEM_ACTIVE = 1;
	
	@Autowired  
	private MessageSource messageSource;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired  
	private ItemDao itemDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	
	@Transactional
	public AjaxListResponse getCustomerAjaxListResponse(
			CustomerFilterObj filter,
			DataTableParameterObj dataTableParameters
			) {
		
		log.debug("| getCustomerAjaxListResponse | entry");
		
		Specification<CustomerDTO> searchSpec = CustomerSpecifications.getCustomerListByFilter(filter);
        List<CustomerDTO> searchResults = customerDao.findAll(searchSpec);
		Long totalRecords = (long)searchResults.size();
		
		Pageable pageRequest =  setUpPageRequest(dataTableParameters);
		Page<CustomerDTO> onePageSearchResults = customerDao.findAll(searchSpec, pageRequest);
		
		AjaxListResponse resp = setUpAjaxListResponse(dataTableParameters.getsEcho(), totalRecords, onePageSearchResults);
		
		log.debug("| getCustomerAjaxListResponse | exit");
		return resp;
	}

	private Pageable setUpPageRequest(DataTableParameterObj dataTableParameters) {
		
		log.debug("| CustomerServiceImpl | setUpPageRequest | entry");
		
		String columnName = ORGANIZATION_COLUMN_0;
		switch(dataTableParameters.getiSortCol_0()) {
			case 0: columnName=ORGANIZATION_COLUMN_0;
					break;
			case 1: columnName=ORGANIZATION_COLUMN_1;
					break;
			case 2: columnName=ORGANIZATION_COLUMN_2;
					break;
			case 3: columnName=ORGANIZATION_COLUMN_3;
					break;
			case 4: columnName=ORGANIZATION_COLUMN_4;
					break;
			case 5: columnName=ORGANIZATION_COLUMN_5;
					break;
			case 6: columnName=ORGANIZATION_COLUMN_6;
					break;
			default: columnName=ORGANIZATION_COLUMN_0;
					break;
		}
		
		int displayLength = dataTableParameters.getiDisplayLength();
		int pageIndex = dataTableParameters.getiDisplayStart()/displayLength;
		
		Direction direction = Sort.Direction.ASC;
		
		if(StringUtils.equals(dataTableParameters.getsSortDir_0(), SORT_DIRECTION)) {
			direction = Sort.Direction.ASC;
		}else {
			direction = Sort.Direction.DESC;
		}
		
		log.debug("| CustomerServiceImpl | setUpPageRequest | exit");
		return new PageRequest(pageIndex, displayLength, direction, columnName);
	}
	
	private AjaxListResponse setUpAjaxListResponse(
			String sEcho, Long totalRecords,
			Page<CustomerDTO> onePageSearchResults
			) {
		
		log.debug("| CustomerServiceImpl | AjaxListResponse | entry");
		
		AjaxListResponse resp = new AjaxListResponse();
		resp.setsEcho(sEcho);
		resp.setiTotalRecords(totalRecords);
		resp.setiTotalDisplayRecords(totalRecords);
		List<Map<String, Object>> aaList = new LinkedList<Map<String, Object>>();
		for (CustomerDTO c : onePageSearchResults) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("0", c.getCustomerId());
			m.put("1", c.getIdentificationNum());
			m.put("2", c.getInvoiceNum());
			m.put("3", LongWrapUtils.LongtoWrap(c.getName()));
			m.put("4", LongWrapUtils.LongtoWrap(c.getEvent()));
			m.put("5", c.getContactNum());
			m.put("6", LongWrapUtils.LongtoWrap(c.getEmail()));
			m.put("7", "<button class='fa fa-pencil btn btn-success editButton'></button>");
			m.put("DT_RowId", c.getCustomerId());
			aaList.add(m);
		}
	
		resp.setAaData(aaList);
		log.debug("| CustomerServiceImpl | AjaxListResponse | exit");
		return resp;
	}
	
	@Transactional
	public void createCustomer(CustomerDTO customerDTO) throws Exception{
		
		customerDTO.setEditCounter(1);
		customerDTO.setRentalCollectDate(new Date());
		customerDTO.setCreatedDate(new Date());
		customerDTO.setLastUpdated(new Date());
		customerDao.save(customerDTO);
		customerDTO.setInvoiceNum(constructInvoiceNumByCustomerId(customerDTO.getCustomerId()));
		customerDao.save(customerDTO);
		
	}
	
	@Transactional
	public CustomerDTO findByCustomerId(Long id) {
		
		return customerDao.findOne(id);
		
	}
	
	@Transactional
	public void editCustomer(CustomerDTO customerDTO) throws Exception{
		
		customerDTO.setEditCounter(customerDTO.getEditCounter()+1);
		if(customerDTO.getEditCounter()==1) {
			customerDTO.setRentalCollectDate(new Date());
		}
		customerDTO.setLastUpdated(new Date());
		customerDao.save(customerDTO);
		
	}

	@Transactional
	public void purgeCustomerRecords() throws Exception{
		
		 //calculate date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Date previousYear = cal.getTime();

		customerDao.deleteByCreatedDateLessThan(previousYear);
		transactionDao.deleteBylastUpdatedLessThan(previousYear);
		
	}
	
	@Transactional
	public void deleteCustomerByIdList(String idList) throws Exception{
		
		String[] splitId = null;
		splitId = idList.split(",");
		for (int i = 0; i < splitId.length; i++) {
			CustomerDTO customer = customerDao.findOne(Long.valueOf(splitId[i]));
			customerDao.delete(Long.valueOf(splitId[i]));
			log.info("| CustomerServiceImpl | deleteCustomerByIdList() | customer identification No. ="+customer.getIdentificationNum()+" was deleted.");
			log.info("| CustomerServiceImpl | deleteCustomerByIdList() | customer detail ="+customer.toString());
			transactionDao.deleteByCustomerId(Long.valueOf(splitId[i]));
		}
		
	}
	
	@Transactional
	public String getItemListJsonString() throws JsonProcessingException {
		
		List<ItemDTO> itemList = itemDao.findByStatus(ITEM_ACTIVE);
		int[] quantityArray = new int[itemList.size()];
		for(int i = 0; i < quantityArray.length; i++) {
			quantityArray[i] = 0;
		}
		return itemListJsonBuilder(itemList, quantityArray);
		
	}

	@Transactional
	public String getItemListJsonStringByCustomerId(Long id) throws JsonProcessingException{
		
		List<TransactionDTO> transactionList = transactionDao.findByCustomerId(id);
		List<ItemDTO> itemList = new ArrayList<ItemDTO>();
		int[] quantityArray = new int[transactionList.size()];
		for(int i = 0; i<transactionList.size(); i++) {
			ItemDTO item = itemDao.findOne(transactionList.get(i).getItemId());
			itemList.add(item);
			quantityArray[i] = transactionList.get(i).getItemQuantity();
		}
		
		return itemListJsonBuilder(itemList, quantityArray);
		
	}
	
	@Transactional
	public List<CustomerDTO> getDownloadListByFilter(CustomerFilterObj filter) {
		
		log.debug("| getDownloadListByFilter | entry");
		
		Specification<CustomerDTO> searchSpec = CustomerSpecifications.getCustomerListByFilter(filter);
		List<CustomerDTO> searchResults = customerDao.findAll(searchSpec);
		
		log.debug("| getDownloadListByFilter | exit");
		return searchResults;
	}
	
	@Transactional
	public String[] setUpCSVHeader() {
		
		List<ItemDTO> itemList = itemDao.findAll();
		List<String> headerList = new ArrayList<String>();
		headerList.add("Customer ID");
		headerList.add("Identification No.");
		headerList.add("Invoice No.");
		headerList.add("School");
		headerList.add("Name");
		headerList.add("Faculty");
		headerList.add("Contact No.");
		headerList.add("Event");
		headerList.add("Address");
		headerList.add("Email");
		headerList.add("Height(cm)");
		headerList.add("Weight(kg)");
		headerList.add("Size");
		headerList.add("Rental($)");
		headerList.add("Deposit($)");
		headerList.add("Rental From");
		headerList.add("Rental To");
		headerList.add("Rental Venue");
		headerList.add("Gown Collect Date");
		headerList.add("Gown Return Date");
		headerList.add("Deposit Return Date");
		headerList.add("JS Receipt No.");
		headerList.add("Created Date");
		headerList.add("Last Updated");
		headerList.add("Remarks");
		for(ItemDTO item : itemList) {
			headerList.add(item.getItemName()+" Price($)");
			headerList.add(item.getItemName()+" Quantity");
		}
		headerList.add("Total($)");
		String[] header = headerList.toArray(new String[headerList.size()]);
		return header;
		
	}
	
	public CellProcessor[] setUpWriterProcessors(String[] header) {
		final CellProcessor[] whiterProcessors = new CellProcessor[header.length];

		for(int i=0; i< header.length; i++){
			  whiterProcessors[i]=new Optional();

		}
		return whiterProcessors;
	}
	
	@Transactional
	public List<Map<String, Object>> convertCustomerListToMapList(
			String[] header,
			List<CustomerDTO> customerList) {
		List<ItemDTO> itemList = itemDao.findAll();
		List<Map<String, Object>> customerMap = new ArrayList<Map<String, Object>>();
		for(CustomerDTO customer : customerList) {
			List<TransactionDTO> transactionList = transactionDao.findByCustomerId(customer.getCustomerId());
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put(header[0], customer.getCustomerId());
	        map.put(header[1], customer.getIdentificationNum());
	        map.put(header[2], customer.getInvoiceNum());
	        map.put(header[3], customer.getSchool());
	        map.put(header[4], customer.getName());
	        map.put(header[5], customer.getFaculty());
	        map.put(header[6], customer.getContactNum());
	        map.put(header[7], customer.getEvent());
	        map.put(header[8], customer.getAddress());
	        map.put(header[9], customer.getEmail());
	        map.put(header[10], customer.getHeight());
	        map.put(header[11], customer.getWeight());
	        map.put(header[12], customer.getSize());
	        map.put(header[13], customer.getRental());
	        map.put(header[14], customer.getDeposit());
	        map.put(header[15], customer.getRentalFrom() == null ? null : "=\"" + SDF_DD_MM_YYYY.format(customer.getRentalFrom())+ "\"");
	        map.put(header[16], customer.getRentalFrom() == null ? null : "=\"" + SDF_DD_MM_YYYY.format(customer.getRentalTo())+ "\"");
	        map.put(header[17], customer.getRentalVenue());
	        map.put(header[18], customer.getRentalCollectDate() == null ? null : "=\"" + SDF_STANDARD.format(customer.getRentalCollectDate())+ "\"");
	        map.put(header[19], customer.getRentalReturnDate() == null ? null : "=\"" + SDF_STANDARD.format(customer.getRentalReturnDate())+ "\"");
	        map.put(header[20], customer.getDepositReturnDate() == null ? null : "=\"" + SDF_STANDARD.format(customer.getDepositReturnDate())+ "\"");
	        map.put(header[21], customer.getJsReceiptNum());
	        map.put(header[22], customer.getCreatedDate() == null ? null : "=\"" + SDF_STANDARD.format(customer.getCreatedDate())+ "\"");
	        map.put(header[23], customer.getLastUpdated() == null ? null : "=\"" + SDF_STANDARD.format(customer.getLastUpdated())+ "\"");
	        map.put(header[24], customer.getRemarks());
	        double total = 0.0;
	        for(ItemDTO item : itemList) {
	        	map.put(item.getItemName()+" Price($)", item.getItemPrice());
	        	int quantity = 0;
	        	for(TransactionDTO transaction : transactionList) {
	        		if(item.getItemId() == transaction.getItemId()) {
	        			quantity = transaction.getItemQuantity();
	        			if(quantity > 0) {
	        				total += quantity * item.getItemPrice();
	        			}
	        			break;
	        		}
	        	}
	        	map.put(item.getItemName()+" Quantity", quantity);
	        }
	        map.put("Total($)", total);
	        customerMap.add(map);
		}
		return customerMap;
	}
	
	private String itemListJsonBuilder(List<ItemDTO> itemList, int[] quantityArray)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		jsonString += "[";
		for(int i=0; i<itemList.size(); i++) {
			ItemJsonObject itemJsonObject = transferItemDTOToItemJsonObject(itemList.get(i), quantityArray[i]);
			if(i==itemList.size()-1) {
				jsonString += mapper.writeValueAsString(itemJsonObject);
			}else {
				jsonString += mapper.writeValueAsString(itemJsonObject)+",";
			}
			
		}
		jsonString += "]";
		log.debug("| CustomerServiceImpl | itemListJsonBuilder() | jsonString: " + jsonString);
		return jsonString;
	}

	private ItemJsonObject transferItemDTOToItemJsonObject(ItemDTO item, int quantity) {
		ItemJsonObject itemJsonObject = new ItemJsonObject();
		itemJsonObject.setItemId(item.getItemId());
		itemJsonObject.setItemName(item.getItemName());
		itemJsonObject.setItemPrice(item.getItemPrice());
		itemJsonObject.setItemQuantity(quantity);
		return itemJsonObject;
	}

	
}
