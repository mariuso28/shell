package org.shell.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CrossScriptingFilter implements Filter{

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	if (request instanceof HttpServletRequest){
    		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    		chain.doFilter(new HtmlSanitizerRequestWrapper(httpServletRequest), response);
    	}
    }

}