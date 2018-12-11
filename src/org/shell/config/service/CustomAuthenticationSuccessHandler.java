package org.shell.config.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private static final Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		log.info("In onAuthenticationSuccess");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("got user : " + user.getUsername() + " authorities : " + user.getAuthorities().toString());
		
		HttpSession session = request.getSession();
		session.setAttribute("email",user.getUsername());
		
		String authorities = user.getAuthorities().toString();
		if ( authorities.contains("ROLE_ADMIN"))
			setDefaultTargetUrl("/web/admin/goAdminHome");
		else
		if ( authorities.contains("ROLE_PUNTER"))
			setDefaultTargetUrl("/web/punter/goPunterHome");
		else
			setDefaultTargetUrl("/web/anon//signin?error&message=" + "Unknown role fo : ".replace(" ","%20") + user.getUsername());
		
		setAlwaysUseDefaultTargetUrl(true);
		request.getSession().setMaxInactiveInterval(5*60);
		log.info("Sending to : " + getDefaultTargetUrl());
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
