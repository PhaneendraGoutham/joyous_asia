package com.joyousasia.form.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController extends BaseController {

//	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	protected static final String HOMEFOLDER = "home/";


	@Override
	protected String getModuleFolder() {
		return HOMEFOLDER;
	}
	
	@RequestMapping(value = "index" + SPRING_EXT)
	public String home() {
		return goToPageJsp("index");
	}
	
	@RequestMapping(value = "main" + SPRING_EXT)
	public String main() {
		
		return redirect("customer/list"+ SPRING_EXT);
		
	}
	

}
