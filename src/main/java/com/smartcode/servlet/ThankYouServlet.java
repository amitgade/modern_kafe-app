package com.smartcode.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;

@WebServlet("/thankYou.html")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user"}))
public class ThankYouServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession session = req.getSession();
		
		Long orderId = (Long) session.getAttribute("orderId");
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		
		Double total = menuDao.getOrderTotal(orderId);
		String status = menuDao.getOrder(orderId).getStatus();

		if (total == null) {
			resp.sendRedirect("/order.html");
		}
		
		req.setAttribute("total", total);
		req.setAttribute("status", status);
		
		// if in case you want to format
//		req.setAttribute("currency", "USD");
		
		// we are done with business logic; now hand it over to jsp for display
		ServletContext context = getServletContext();
		RequestDispatcher dispatch = context.getRequestDispatcher("/thankYou.jsp");
		dispatch.forward(req, resp);
	}

}
