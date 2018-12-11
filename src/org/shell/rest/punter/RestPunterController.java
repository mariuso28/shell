package org.shell.rest.punter;

import org.apache.log4j.Logger;
import org.shell.rest.anon.ResultJson;
import org.shell.services.Services;
import org.shell.user.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/punter")
public class RestPunterController {
	private static Logger log = Logger.getLogger(RestPunterController.class);
	@Autowired
	private Services services;
	
	@RequestMapping(value = "/getDetails")
	// PkfzResultJson contains punter base user if success, message if fail
	public ResultJson getDetails(OAuth2Authentication auth)
	{
		String email = ((User)auth.getPrincipal()).getUsername();
		log.info("Received getDetails for : " + email);
		
		ResultJson result = new ResultJson();
		try
		{
			BaseUser bu = services.getHome().getBaseUserDao().getByEmail(email);
			result.success(bu);
		}
		catch (Exception e)
		{
			result.fail(e.getMessage());
		}
		
		return result;
	}
}