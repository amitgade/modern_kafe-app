package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;
import com.smartcode.domain.Order;

public class KitchenAsyncTask implements Runnable {

	private AsyncContext asyncContext;

	public void setAsyncContext(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
	}

	@Override
	public void run() {
		
		// get the order from back-end
		HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
		
		Long size = Long.parseLong(request.getParameter("size"));
		MenuDao dao = MenuDaoFactory.getMenuDao();
		
		while (dao.getAllOrders().size() < size) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				asyncContext.complete();
				throw new RuntimeException(e);
			}
		}
		
		Order order = dao.getOrder(size);
		
		// Displaying work
		PrintWriter out;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			asyncContext.complete();
			throw new RuntimeException(e);
		}
		response.setContentType("text/html");
		out.println("<p><strong>Next Order:</string><br/>" + order.toString() + "</p>");
		out.close();
		
		asyncContext.complete();
		
	}

}
