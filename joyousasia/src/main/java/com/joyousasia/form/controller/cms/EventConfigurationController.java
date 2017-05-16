package com.joyousasia.form.controller.cms;

/**
 * Author: Vincent
 */


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyousasia.common.AjaxListResponse;
import com.joyousasia.form.controller.BaseController;
import com.joyousasia.model.filter.DataTableParameterObj;
import com.joyousasia.service.cms.EventServiceImpl;
import com.joyousasia.service.cms.FacultyServiceImpl;
import com.joyousasia.service.cms.SchoolServiceImpl;


@Controller
public class EventConfigurationController extends BaseController
{
	private static final Logger log = Logger.getLogger(EventConfigurationController.class);

	@Autowired  
	private EventServiceImpl eventService;
	
	@Autowired  
	private SchoolServiceImpl schoolService;
	
	@Autowired  
	private FacultyServiceImpl facultyService;
	
	@Override
	protected String getModuleFolder() {
		return "event/";
	}

	
	@RequestMapping(value = "event/event_config" + SPRING_EXT, method = RequestMethod.GET)
	public String event_config(
			) {
		
		log.debug("| EventConfigurationController | event/event_config | entry");
		log.debug("| EventConfigurationController | event/event_config | exit");
		return goToPageJsp("event_config");

	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_event_list" + SPRING_EXT, headers = "Accept=application/json")
	public AjaxListResponse ajax_event_list(
			String sEcho, 
			int iDisplayStart, 
			int iDisplayLength,
			int iSortCol_0,
			String sSortDir_0,
			String sSearch,
			HttpServletRequest req
			) 
	{

		log.debug("| EventConfigurationController | event/ajax_event_list | entry");
		
		DataTableParameterObj dataTableParameters = new DataTableParameterObj(sEcho, iDisplayStart, 
				iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
		
		AjaxListResponse resp = eventService.getEventAjaxListResponse(dataTableParameters);
		
		log.info("| EventConfigurationController | event/ajax_event_list | NO PARAMETER | List successfully.");
		log.debug("| EventConfigurationController | event/ajax_event_list | exit");
		return resp;

	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_save_event" + SPRING_EXT, headers = "Accept=application/json")
	public String event_save_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| EventConfigurationController | item/ajax_save_event | POST | entry");
		String errmsg = "Save failed!";
		try {
			log.debug("| EventConfigurationController | event/ajax_save_event | POST | Item ID: "+ req.getParameter("eventId"));
			log.debug("| EventConfigurationController | event/ajax_save_event | POST | Status: "+ req.getParameter("status"));
			
			eventService.saveEventByEventIdAndStatus(Integer.valueOf(req.getParameter("eventId")), Integer.valueOf(req.getParameter("status")));
			
			log.debug("| EventConfigurationController | event/ajax_save_event | Saved into Database");
			log.debug("| EventConfigurationController | event/ajax_save_event | POST | exit");
			errmsg = "Save Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| EventConfigurationController | event/ajax_save_event | POST |Save Event|Save failed.");
			return errmsg;
		}
        
	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_inactive_all_events" + SPRING_EXT, headers = "Accept=application/json")
	public String inactive_all_events_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| EventConfigurationController | event/ajax_inactive_all_events | GET | entry");
		String errmsg = "Inactive All Events failed!";
		try {
			
			eventService.inactiveAllEvents();
			
			log.debug("| EventConfigurationController | event/ajax_inactive_all_events | Saved into Database");
			log.debug("| EventConfigurationController | event/ajax_inactive_all_events | GET | exit");
			errmsg = "Inactive All Events Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| EventConfigurationController | item/ajax_inactive_all_events | POST |Save Event|Save failed.");
			return errmsg;
		}
        
	}
	
	@RequestMapping(value = "event/school_config" + SPRING_EXT, method = RequestMethod.GET)
	public String school_config(
			) {
		
		log.debug("| EventConfigurationController | event/school_config | entry");
		log.debug("| EventConfigurationController | event/school_config | exit");
		return goToPageJsp("school_config");

	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_school_list" + SPRING_EXT, headers = "Accept=application/json")
	public AjaxListResponse ajax_school_list(
			String sEcho, 
			int iDisplayStart, 
			int iDisplayLength,
			int iSortCol_0,
			String sSortDir_0,
			String sSearch,
			HttpServletRequest req
			) 
	{

		log.debug("| EventConfigurationController | event/ajax_school_list | entry");
		
		DataTableParameterObj dataTableParameters = new DataTableParameterObj(sEcho, iDisplayStart, 
				iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
		
		AjaxListResponse resp = schoolService.getSchoolAjaxListResponse(dataTableParameters);
		
		log.info("| EventConfigurationController | event/ajax_school_list | NO PARAMETER | List successfully.");
		log.debug("| EventConfigurationController | event/ajax_school_list | exit");
		return resp;

	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_save_school" + SPRING_EXT, headers = "Accept=application/json")
	public String school_save_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| EventConfigurationController | item/ajax_save_school | POST | entry");
		String errmsg = "Save failed!";
		try {
			log.debug("| EventConfigurationController | event/ajax_save_school | POST | Item ID: "+ req.getParameter("schoolId"));
			log.debug("| EventConfigurationController | event/ajax_save_school | POST | Status: "+ req.getParameter("status"));
			
			schoolService.saveSchoolBySchoolIdAndStatus(Integer.valueOf(req.getParameter("schoolId")), Integer.valueOf(req.getParameter("status")));
			
			log.debug("| EventConfigurationController | event/ajax_save_school | Saved into Database");
			log.debug("| EventConfigurationController | event/ajax_save_school | POST | exit");
			errmsg = "Save Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| EventConfigurationController | event/ajax_save_school | POST |Save School|Save failed.");
			return errmsg;
		}
        
	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_inactive_all_schools" + SPRING_EXT, headers = "Accept=application/json")
	public String inactive_all_schools_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| EventConfigurationController | event/ajax_inactive_all_schools | GET | entry");
		String errmsg = "Inactive All Schools failed!";
		try {
			
			schoolService.inactiveAllSchools();
			
			log.debug("| EventConfigurationController | event/ajax_inactive_all_schools | Saved into Database");
			log.debug("| EventConfigurationController | event/ajax_inactive_all_schools | GET | exit");
			errmsg = "Inactive All Schools Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| EventConfigurationController | item/ajax_inactive_all_schools | POST |Save School|Save failed.");
			return errmsg;
		}
        
	}
	
	@RequestMapping(value = "event/faculty_config" + SPRING_EXT, method = RequestMethod.GET)
	public String faculty_config(
			) {
		
		log.debug("| EventConfigurationController | event/faculty_config | entry");
		log.debug("| EventConfigurationController | event/faculty_config | exit");
		return goToPageJsp("faculty_config");

	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_faculty_list" + SPRING_EXT, headers = "Accept=application/json")
	public AjaxListResponse ajax_faculty_list(
			String sEcho, 
			int iDisplayStart, 
			int iDisplayLength,
			int iSortCol_0,
			String sSortDir_0,
			String sSearch,
			HttpServletRequest req
			) 
	{

		log.debug("| EventConfigurationController | event/ajax_faculty_list | entry");
		
		DataTableParameterObj dataTableParameters = new DataTableParameterObj(sEcho, iDisplayStart, 
				iDisplayLength, iSortCol_0, sSortDir_0, sSearch);
		
		AjaxListResponse resp = facultyService.getFacultyAjaxListResponse(dataTableParameters);
		
		log.info("| EventConfigurationController | event/ajax_faculty_list | NO PARAMETER | List successfully.");
		log.debug("| EventConfigurationController | event/ajax_faculty_list | exit");
		return resp;

	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_save_faculty" + SPRING_EXT, headers = "Accept=application/json")
	public String faculty_save_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| EventConfigurationController | item/ajax_save_faculty | POST | entry");
		String errmsg = "Save failed!";
		try {
			log.debug("| EventConfigurationController | event/ajax_save_faculty | POST | Item ID: "+ req.getParameter("facultyId"));
			log.debug("| EventConfigurationController | event/ajax_save_faculty | POST | Status: "+ req.getParameter("status"));
			
			facultyService.saveFacultyByFacultyIdAndStatus(Integer.valueOf(req.getParameter("facultyId")), Integer.valueOf(req.getParameter("status")));
			
			log.debug("| EventConfigurationController | event/ajax_save_faculty | Saved into Database");
			log.debug("| EventConfigurationController | event/ajax_save_faculty | POST | exit");
			errmsg = "Save Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| EventConfigurationController | event/ajax_save_faculty | POST |Save Faculty|Save failed.");
			return errmsg;
		}
        
	}
	
	@ResponseBody
	@RequestMapping(value = "event/ajax_inactive_all_facultys" + SPRING_EXT, headers = "Accept=application/json")
	public String inactive_all_facultys_submit(
			HttpServletRequest req
			) 
	{
		log.debug("| EventConfigurationController | event/ajax_inactive_all_facultys | GET | entry");
		String errmsg = "Inactive All Facultys failed!";
		try {
			
			facultyService.inactiveAllFacultys();
			
			log.debug("| EventConfigurationController | event/ajax_inactive_all_facultys | Saved into Database");
			log.debug("| EventConfigurationController | event/ajax_inactive_all_facultys | GET | exit");
			errmsg = "Inactive All Facultys Successful!";
	        return errmsg;
			
		} catch (Exception e) {
			log.info(e,e);
			log.info("| EventConfigurationController | item/ajax_inactive_all_facultys | POST |Save Faculty|Save failed.");
			return errmsg;
		}
        
	}
	
}
	