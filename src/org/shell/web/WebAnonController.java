package org.shell.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web/anon")
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
	
	@RequestMapping(value = "/getPunter", method = RequestMethod.GET)
	public String getPunter() {
			
		log.info("Received getPunter");
			
		return "punter";
	}
	
}
