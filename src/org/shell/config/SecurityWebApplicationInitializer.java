package org.shell.config;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new MultipartFilter());
		//insertFilters(servletContext, new HiddenHttpMethodFilter());
	//	insertFilters(servletContext, new CrossScriptingFilter());
	}
	
	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		//insertFilters(servletContext, new MultipartFilter());
		//insertFilters(servletContext, new HiddenHttpMethodFilter());
		insertFilters(servletContext, new CrossScriptingFilter());
	}
}