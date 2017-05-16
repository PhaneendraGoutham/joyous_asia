package com.joyousasia.form.controller.cms;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.joyousasia.common.CSVFileHandler;
import com.joyousasia.form.controller.BaseController;



/**
 * Author: Vincent
 */

@Controller
public class UploadController extends BaseController 
{

	private static final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private CSVFileHandler cSVFileHandler;
	
	@Autowired  
	private MessageSource messageSource;
	
	@Override
	protected String getModuleFolder() {
		return "csv/";
	}
	
	/*
	 * function: csv_upload
	 * Load when the upload.jsp is open
	 */
	@RequestMapping(value = "csv/upload" + SPRING_EXT, method = RequestMethod.GET)
	public String batch_upload(
			Model model
			) 
		{
			log.info("| csv_upload | entry");
			log.info("| csv_upload | exit");
			return goToPageJsp("upload");
		}
	
	/*
	 * function: batchUpload
	 * Load after user click submit the page
	 */
	
	@RequestMapping(value = "csv/upload" + SPRING_EXT, method = RequestMethod.POST)
	public String batch_upload_submit(
			Model model,
			HttpServletRequest req,
			@RequestParam("upload") MultipartFile file
			) throws Exception 
	{
		
		log.info("| csv_upload | entry");
		log.debug("file: "+file);
		
		String errorMsg = "";
		
		try {
			
			File csvFile = new File( file.getOriginalFilename());
			file.transferTo(csvFile);
			errorMsg = cSVFileHandler.handleCustomer(csvFile);
			
		} catch (Exception e) {
			
			errorMsg = e.getMessage();
			e.printStackTrace();
			
		}
		
		if(StringUtil.isBlank(errorMsg)) {
			model.addAttribute("errmsg", "Upload Successful!");
		}else {
			model.addAttribute("errmsg", errorMsg);
		}
		
		return batch_upload(model);

	}
	
}



class CSVConditionFilterException extends Exception {
/**
 * 
 */
	private static final long serialVersionUID = 3090722898779447472L;

	CSVConditionFilterException(String s) {
		super(s);
   }
}