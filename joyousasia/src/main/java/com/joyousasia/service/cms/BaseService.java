package com.joyousasia.service.cms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class BaseService implements ServletContextAware{

	protected static final Logger log = Logger.getLogger(BaseService.class);
	
	public static final String SPRING_EXT = ".htm";
	protected static final String SORT_DIRECTION = "asc";
	protected static final DateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	protected static final DateFormat SDF_DD_MMM_YYYY = new SimpleDateFormat("dd-MMM-yyyy");
	protected static final DateFormat SDF_DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
	protected static final DateFormat SDF_STANDARD = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	protected ServletContext servletContext;  
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public HttpServletRequest getCurrentRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public String getCurrentUrl(){
		HttpServletRequest request = getCurrentRequest();
		return request.getRequestURL().toString();
	}
	
	protected String constructInvoiceNumByCustomerId(Long cusotmerId) {
		String invoiceNum = "";
        String costomerIdString = cusotmerId.toString();
        if(costomerIdString.length()<6) {
        	StringBuilder sb = new StringBuilder();
        	for (int toPrepend=6-costomerIdString.length(); toPrepend>0; toPrepend--) {
        	    sb.append('0');
        	}
        	sb.append(costomerIdString);
        	String costomerIdformatted = sb.toString();
        	invoiceNum = "JE/"+ costomerIdformatted;
        }else {
        	invoiceNum = "JE/"+ costomerIdString;
        }
        return invoiceNum;
	}

}