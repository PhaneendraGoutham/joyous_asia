package com.joyousasia.form.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;


@Controller
public abstract class BaseController {
	
	public static final String SPRING_EXT = ".joyous";
	public static final String SPRING_REDIRECT_PREFIX = "redirect:/";
	
	protected static final DateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	protected static final DateFormat SDF_DD_MMM_YYYY = new SimpleDateFormat("dd-MMM-yyyy");
	protected static final DateFormat SDF_DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
	protected static final DateFormat SDF_STANDARD = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	/**
	 * 
	 * @return subfolder located in "/WEB-INF/views/" for this current module
	 */
	protected abstract String getModuleFolder();
	
	protected String redirectToPageSpring(String strURL) {
		return SPRING_REDIRECT_PREFIX + strURL;
	}
	
	protected String goToPageJsp(String strURL) {
		return getModuleFolder() + strURL;
	}
	
	protected String redirect(String url) {
		return "redirect:"+url;
	}
	
	protected String getCurrentUnixTimestamp() {
		Date currentDate = new Date();
		Long unixTimestamp = currentDate.getTime() / 1000;
		return unixTimestamp.toString();
	}
	
}
