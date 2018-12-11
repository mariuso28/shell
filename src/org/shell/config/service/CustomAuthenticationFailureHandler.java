package org.shell.config.service;


import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.cz.validate.Validator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private static final Logger log = Logger.getLogger(CustomAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, 
			AuthenticationException exception)
	{
		String email = request.getParameter("email");
		if (Validator.isValidEmailAddress(email))
			setDefaultFailureUrl("/Dx4/logon/signin?error&message=Authentication%20Error");
		else
			setDefaultFailureUrl("/Dx4/logon/signin?error&message=Authentication%20Error");
		
		log.info("Failure on : " + request.getParameter("email"));
		setUseForward(false);
		
		try {
			log.info("Forwarding to : " + "/Dx4/logon/signin?error&message=Authentication%20Error");
			super.onAuthenticationFailure(request, response, exception);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
