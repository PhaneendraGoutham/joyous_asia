package com.joyousasia.form.controller.cms;

/**
 * Author: Vincent
 */


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyousasia.common.AjaxListResponse;
import com.joyousasia.form.controller.BaseController;
import com.joyousasia.model.filter.DataTableParameterObj;
import com.joyousasia.service.cms.ItemServiceImpl;


@Controller
public class ItemController extends BaseController
{
	private static final Logger log = Logger.getLogger(ItemController.class);

	@Autowired  
	private ItemServiceImpl itemService;
	
	@Override
	protected String getModuleFolder() {
		return "item/";
	}

	@RequestMapping(value = { "item/index" + SPRING_EXT, "item/list" + SPRING_EXT })
	public String index(
			) {
		
		log.debug("| ItemController | item/list | entry");
		log.debug("| ItemController | item/list | exit");
		return goToPageJsp("list");

	}
	
	@ResponseBody
	@RequestMapping(value = "item/ajax_list" + SPRING_EXT, headers = "Accept=application/json")
	public AjaxListResponse ajax_item_list(
			String sEcho, 
			int iDisplayStart, 
			int iDisplayLength,
			int iSortCol_0,
			String sSortDir_0,
			String sSearch,
			HttpServletRequest req
			) 
	{

		log.debug("| ItemController | item/ajax_item_list | entry");
		
		DataTableParameterObj dataTableParameters = new DataTableParameterObj(sEcho, iDisplayStart, 
				iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
		
		AjaxListResponse resp = itemService.getItemAjaxListResponse(dataTableParameters);
		
		log.info("| ItemController | item/ajax_item_list | NO PARAMETER | List successfully.");
		log.debug("| ItemController | item/ajax_item_list | exit");
		return resp;

	}
	
	@ResponseBody
	@RequestMapping(value = "item/ajax_save_item" + SPRING_EXT, headers = "Accept=application/json")
	public String item_save_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| ItemController | item/ajax_save_item | POST | entry");
		String errmsg = "Save failed!";
		try {
			log.debug("| ItemController | item/ajax_save_item | POST | Item ID: "+ req.getParameter("itemId"));
			log.debug("| ItemController | item/ajax_save_item | POST | Item Name: "+ req.getParameter("itemName"));
			log.debug("| ItemController | item/ajax_save_item | POST | Item Name: "+ req.getParameter("itemPrice"));
			
			itemService.saveItem(req.getParameter("itemId"), req.getParameter("itemName"), req.getParameter("itemPrice"), req.getParameter("status"));
			
			log.debug("| ItemController | item/ajax_save_item | Saved into Database");
			log.debug("| ItemController | item/ajax_save_item | POST | exit");
			errmsg = "Save Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| ItemController | item/ajax_save_item | POST |Save Item|Save failed.");
			return errmsg;
		}
        
	}
	
	@ResponseBody
	@RequestMapping(value = "item/ajax_delete_item" + SPRING_EXT, headers = "Accept=application/json")
	public String item_delete_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| ItemController | item/ajax_delete_item | POST | entry");
		String errmsg = "Delete failed!";
		try {
			log.debug("| ItemController | item/ajax_delete_item | POST | Item ID String: "+ req.getParameter("id"));
			
			String itemInTransactionRecord = itemService.batchDeleteByItemIdList(req.getParameter("id"));
			
			log.debug("| ItemController | item/ajax_delete_item | Deleted from Database");
			log.debug("| ItemController | item/ajax_delete_item | POST | exit");
			
			if(!StringUtil.isBlank(itemInTransactionRecord)) {
				errmsg = "Delete failed! "+itemInTransactionRecord+" have(has) transaction record in database.";
			}else {
				errmsg = "Delete Successful!";
			}
			
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| ItemController | item/ajax_delete_item | POST |Save Item|Delete failed.");
			return errmsg;
		}
        
	}
	
}
	