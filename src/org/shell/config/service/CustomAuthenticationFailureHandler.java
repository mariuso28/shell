package org.shell.config.service;


import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
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
		log.info("Failure on : " + request.getParameter("email"));
		setUseForward(false);
		
		try {
			log.info("Forwarding to : " + "/web/anon//signin?error&message==Authentication%20Error");
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
