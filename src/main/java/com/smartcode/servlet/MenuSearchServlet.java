package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDataService;
import com.smartcode.domain.MenuItem;

public class MenuSearchServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		String searchTerm = req.getParameter("searchTerm");

		MenuDataService menuDataService = new MenuDataService();
		List<MenuItem> menuitems = menuDataService.find(searchTerm);

		if (menuitems.size() > 0) {
			out.println("<html><body><h1>Modern Kafe</h1>");
			out.println("<h2>Dishes containing " + searchTerm + "</h2><ul>");
			for (MenuItem menuItem : menuitems) {
				out.println("<li>" + menuItem + " " + menuItem.getDescription() + "</li>");
			}
			out.println("</ul></body></html>");

		} else {
			out.println("<html><body><h1>Modern Kafe</h1>");
			out.println("<p>I'm sorry, there are no dishes containing  " + searchTerm);
			out.println("</p></body></html>");
		}

		out.close();
	}
}
