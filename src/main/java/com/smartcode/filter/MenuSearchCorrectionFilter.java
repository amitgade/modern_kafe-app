package com.smartcode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/searchResults.html")
public class MenuSearchCorrectionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String searchTerm = request.getParameter("searchTerm");
		if (searchTerm.toLowerCase().equals("asparagus")) {
			
			MenuSearchCorrectionRequestWrapper wrapper = new MenuSearchCorrectionRequestWrapper((HttpServletRequest)request);
			wrapper.setNewSearchterm("vegetable");
			chain.doFilter(wrapper, response);
		}
		
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}
}
