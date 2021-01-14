package com.smartcode.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;
import com.smartcode.domain.MenuItem;

@WebServlet("")
public class ViewMenuServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.getFullMenu();

		req.setAttribute("menuItems", menuItems);
		
		// we are done with business logic; now hand it over to jsp for display
		ServletContext context = getServletContext();
		RequestDispatcher dispatch = context.getRequestDispatcher("/menu.jsp");
		dispatch.forward(req, resp);
	}
}
