package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDataService;
import com.smartcode.domain.MenuItem;

public class OrderServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><h1>Modern Kafe</h1>");
		out.println("<h2>Order your food</h2>");

		out.println("<form action='orderReceived.html' method='POST'>");
		out.println("<ul>");

		MenuDataService menuDataService = new MenuDataService();
		List<MenuItem> menuItems = menuDataService.getFullMenu();

		for (MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "<input type='text' name='item_" + menuItem.getId() + "'/> </li>");
		}

		out.println("</ul>");

		out.println("<input type='submit' />");
		out.println("</input></form></html>");

		out.close();
	}
}
