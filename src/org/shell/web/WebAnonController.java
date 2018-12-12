package org.shell.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/web/anon")

@SessionAttributes({ "errMsg" } )

public class WebAnonController {
	private static Logger log = Logger.getLogger(WebAnonController.class);
	
	@RequestMapping(value = "/getSuccess", method = RequestMethod.GET)
	public String getSuccess() {
			
		log.info("Received getSuccess");
			
		return "success";
	}
	
	@RequestMapping(value = "/logon", method = RequestMethod.GET)
	public String logon() {
			
		log.info("Received logon");
			
		return "logon";
	}
	
	@RequestMapping(value = "/getAdmin", method = RequestMethod.GET)
	public String getAdmin() {
			
		log.info("Received getAdmin");
			
		return "admin";
	}
	
	@RequestMapping(value = "/goAdminHome", method = RequestMethod.GET)
	public String goAdminHome() {
			
		log.info("Received goAdminHome");
			
		return "admin";
	}
	
	@RequestMapping(value = "/getPunter", method = RequestMethod.GET)
	public String getPunter() {
			
		log.info("Received getPunter");
			
		return "punter";
	}
	
	@RequestMapping(value = "/invalidSession", method = RequestMethod.GET)
	public String invalidSession(ModelMap model) {
		
		log.info("Received invalidSession");
		model.addAttribute("errMsg", "Invalid Session");
		return "error";
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(ModelMap model) {
		
		log.info("Received accessDenied");
		model.addAttribute("errMsg", "Access Denied");
		return "error";
	}
	
}
