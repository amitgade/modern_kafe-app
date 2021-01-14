package com.smartcode.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MenuSearchCorrectionRequestWrapper extends HttpServletRequestWrapper {

	private String newSearchterm;
	
	public MenuSearchCorrectionRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public void setNewSearchterm(String newSearchterm) {
		this.newSearchterm = newSearchterm;
	}
	
	@Override
	public String getParameter(String key) {
		
		if (key.equals("searchTerm")) {
			/// do something here
			return newSearchterm;
		} else {
			return super.getParameter(key);
		}
	}
}
