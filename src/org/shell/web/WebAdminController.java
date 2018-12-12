package org.shell.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web/admin")
public class WebAdminController {
	private static Logger log = Logger.getLogger(WebAdminController.class);
	
	@RequestMapping(value = "/goAdminHome", method = RequestMethod.GET)
	public String goAdminHome() {
			
		log.info("Received goAdminHome");
			
		return "admin";
	}
	
}
