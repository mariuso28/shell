package org.shell.config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class HtmlSanitizerRequestWrapper extends HttpServletRequestWrapper {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(HtmlSanitizerRequestWrapper.class);
	
//	private final Whitelist customWhiteList = Whitelist.basic().addTags("s");
	
	public HtmlSanitizerRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
	//	logger.info("html sanitizing: " + value);
		String clean = Jsoup.clean(value, Whitelist.none());
	//	logger.info("done sanitizing: " + clean);
		return clean;
	}

}