package com.smartcode.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.getFullMenu();

		request.setAttribute("menuItems", menuItems);
		
		// we are done with business logic; now hand it over to jsp for display
		ServletContext context = getServletContext();
		RequestDispatcher dispatch = context.getRequestDispatcher("/order.jsp");
		dispatch.forward(request, response); 
	}
}
