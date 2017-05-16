package com.joyousasia.form.controller.cms;

/**
 * Author: Vincent
 */


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.joyousasia.form.controller.BaseController;
import com.joyousasia.model.InvoiceReceiptDTO;
import com.joyousasia.service.cms.InvoiceReceiptServiceImpl;


@Controller
public class InvoiceReceiptController extends BaseController
{
	private static final Logger log = Logger.getLogger(InvoiceReceiptController.class);

	@Autowired  
	private InvoiceReceiptServiceImpl invoiceReceiptService;
	
	@Override
	protected String getModuleFolder() {
		return "invoice/";
	}

	@RequestMapping(value = "invoice/config" + SPRING_EXT, method = RequestMethod.GET)
	public String invoice_config(
			Model model
			) {
		
		log.debug("| InvoiceReceiptController | invoice/config | entry");
		
		model.addAttribute("InvoiceReceiptDTO", invoiceReceiptService.getInvoiceReceiptDTO()); // adding in model
		
		log.debug("| InvoiceReceiptController | invoice/config | exit");
		return goToPageJsp("config");

	}
	
	@RequestMapping(value = "invoice/config" + SPRING_EXT, method = RequestMethod.POST)
	public String invoice_config_submit(
			Model model,
			@ModelAttribute("InvoiceReceiptDTO") InvoiceReceiptDTO invoiceReceiptDTO,
			@RequestParam("upload") MultipartFile image,
			HttpServletRequest req
			) 
	{
		log.debug("| InvoiceReceiptController | invoice/config | POST | entry");
		try {
			log.debug("| InvoiceReceiptController | invoice/config | POST | Logo Path: "+ invoiceReceiptDTO.getLogo());
			
			invoiceReceiptService.saveInvoiceReceiptInfomation(invoiceReceiptDTO, image);
			
			log.debug("| InvoiceReceiptController | invoice/config | POST | exit");
	        return redirect("../customer/list"+ SPRING_EXT);
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| InvoiceReceiptController | invoice/config | POST | Config invoice failed.");
			model.addAttribute("errmsg", "Save failed!");
			return invoice_config( model);
		}
        
	}
	
}
	