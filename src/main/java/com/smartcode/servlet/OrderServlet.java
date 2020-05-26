package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;
import com.smartcode.domain.MenuItem;

@WebServlet("/order.html")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user"}))
public class OrderServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><h1>Modern Kafe</h1>");
		out.println("<h2>Order your food</h2>");

		out.println("<form action='orderReceived.html' method='POST'>");
		out.println("<ul>");

		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.getFullMenu();

		for (MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "<input type='text' name='item_" + menuItem.getId() + "'/> </li>");
		}

		out.println("</ul>");

		out.println("<input type='submit' />");
		out.println("</input></form></html>");

		out.close();
	}
}
