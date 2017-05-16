package com.joyousasia.service.cms;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyousasia.common.AjaxListResponse;
import com.joyousasia.dao.cms.ItemDao;
import com.joyousasia.dao.cms.TransactionDao;
import com.joyousasia.model.ItemDTO;
import com.joyousasia.model.TransactionDTO;
import com.joyousasia.model.filter.DataTableParameterObj;

@Service
public class ItemServiceImpl extends BaseService{
	
	private static final Logger log = Logger.getLogger(ItemServiceImpl.class);
	
	private static final String COLUMN_0 = "itemId";
	private static final String COLUMN_1 = "itemName";
	private static final String COLUMN_2 = "itemPrice";
	
	@Autowired  
	private ItemDao itemDao;
	
	@Autowired  
	private TransactionDao transactionDao;
	
	public AjaxListResponse getItemAjaxListResponse(
			DataTableParameterObj dataTableParameters) {
		
		log.debug("| ItemServiceImpl | getItemAjaxListResponse | entry");
		
		List<ItemDTO> searchResults = itemDao.findAll();
		Pageable pageRequest =  setUpPageRequest(dataTableParameters);
		Page<ItemDTO> onePageSearchResults = itemDao.findAll(pageRequest);
		
		Long totalRecords = (long)searchResults.size();
		
		AjaxListResponse resp = setUpAjaxListResponse(dataTableParameters.getsEcho(), totalRecords, onePageSearchResults);
		
		log.debug("| ItemServiceImpl | getItemAjaxListResponse | exit");
		return resp;
		
	}
	
	private Pageable setUpPageRequest(DataTableParameterObj dataTableParameters) {
		
		log.debug("| ItemServiceImpl | setUpPageRequest | entry");
		
		String columnName = COLUMN_0;
		switch(dataTableParameters.getiSortCol_0()) {
			case 0: columnName=COLUMN_0;
					break;
			case 1: columnName=COLUMN_1;
					break;
			case 2: columnName=COLUMN_2;
					break;
			default: columnName=COLUMN_0;
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
		
		log.debug("| ItemServiceImpl | setUpPageRequest | exit");
		return new PageRequest(pageIndex, displayLength, direction, columnName);
	}
	
	private AjaxListResponse setUpAjaxListResponse(
			String sEcho, Long totalRecords,
			Page<ItemDTO> onePageSearchResults) {
		
		log.debug("| ItemServiceImpl  | AjaxListResponse | entry");
		
		String statusActive = "<span style='color:#00ff00'>Active</span>";
		String statusInactive = "<span style='color:#ff0000'>Suspend</span>";
		
		AjaxListResponse resp = new AjaxListResponse();
		resp.setsEcho(sEcho);
		resp.setiTotalRecords(totalRecords);
		resp.setiTotalDisplayRecords(totalRecords);
		List<Map<String, Object>> aaList = new LinkedList<Map<String, Object>>();
		for (ItemDTO i : onePageSearchResults) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("0", i.getItemId());
			m.put("1", i.getItemName());
			m.put("2", i.getItemPrice());
			m.put("3", i.getStatus()==1 ? statusActive : statusInactive);
			m.put("4", i.getStatus());
			m.put("5", "<button class='fa fa-pencil btn btn-success editButton'></button>");
			m.put("DT_RowId", i.getItemId());
			aaList.add(m);
		}
	
		resp.setAaData(aaList);
		log.debug("| ItemServiceImpl  | AjaxListResponse | exit");
		return resp;
	}

	@Transactional
	public ItemDTO saveItem(
			String itemId,
			String itemName,
			String itemPrice,
			String status
			) throws Exception{
		
		log.debug("| ItemServiceImpl | saveItem() | entry");
		
		ItemDTO item = new ItemDTO();
		if(StringUtils.isBlank(itemId)){
			item.setItemName(itemName);
			item.setItemPrice(Double.valueOf(itemPrice));
			item.setStatus(Integer.valueOf(status));
			item.setCreatedDate(new Date());
			item.setLastUpdated(new Date());
		}else {
			item = itemDao.findOne(Integer.valueOf(itemId));
			item.setItemName(itemName);
			item.setItemPrice(Double.valueOf(itemPrice));
			item.setStatus(Integer.valueOf(status));
			item.setLastUpdated(new Date());
		}
		itemDao.save(item);
		log.debug("| ItemServiceImpl | saveItem() | item: "+item.toString());
		log.debug("| ItemServiceImpl | saveItem() | exit");
		return item;
		
	}

	
	@Transactional
	public  String batchDeleteByItemIdList(String idList) {
		String[] splitId = null;
		splitId = idList.split(",");
		String itemIsBroughtByCustomer = "";
		for (int i = 0; i < splitId.length; i++) {
			
			List<TransactionDTO> transactionList = transactionDao.findByItemId(Integer.valueOf(splitId[i]));
			if(transactionList.size()==0) {
				itemDao.delete(Integer.valueOf(splitId[i]));
			}else {
				if(!StringUtil.isBlank(itemIsBroughtByCustomer)) {
					itemIsBroughtByCustomer += "," + itemDao.findOne(Integer.valueOf(splitId[i])).getItemName();
				}else {
					itemIsBroughtByCustomer += itemDao.findOne(Integer.valueOf(splitId[i])).getItemName();
				}
			}
			
		}
		return itemIsBroughtByCustomer;
	}
	
	@Transactional
	public List<ItemDTO> getAllItems() {
		return itemDao.findAll();
	}
	
	
//	@Transactional
//	public boolean checkUniqueRoleName(String ou, String roleName) {
//		
//		boolean isUniqueRoleName = true;
//		List<OrganizationRoleDTO> roleList = organizationRoleDao.findByOrganizationDTO_OuAndRoleDTO_Name(ou, roleName);
//		if(roleList.size()!=0) {
//			isUniqueRoleName = false;
//		}
//		return isUniqueRoleName;
//		
//	}
//	
//	@Transactional
//	public boolean checkUniqueRoleNameExcludeRoleNameIsBeingEditing(Long roleId, String ou, String roleName) {
//		boolean isUniqueRoleName = true;
//		List<OrganizationRoleDTO> roleList = organizationRoleDao.findByOrganizationDTO_OuAndRoleDTO_NameAndRoleDTO_RoleIdNot(ou, roleName, roleId);
//		if(roleList.size()!=0) {
//			isUniqueRoleName = false;
//		}
//		return isUniqueRoleName;
//	}
	
	
}
