package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;
import com.smartcode.domain.MenuItem;

//@WebServlet("")
public class ViewMenuServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.getFullMenu();

		out.println("<html><body><h1>Modern Kafe</h1><ul>");
		out.println("<h2>Menu</h2>");

		for (MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "</li>");
		}

		out.println("</ul>");
		out.println("<a href='searchResults.html?searchTerm=vegetable' >View all of our vegegable dishes</a>");
		out.println("</body></html>");

		out.close();
	}
}
