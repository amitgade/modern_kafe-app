package com.smartcode.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;
import com.smartcode.domain.Order;
import com.smartcode.websockets.KitchenDisplaySessionHandler;
import com.smartcode.websockets.KitchenDisplaySessionHandlerFactory;

@WebServlet("/orderReceived.html")
public class OrderReceivedServlet extends HttpServlet {

	MenuDao menuDao = MenuDaoFactory.getMenuDao();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int maxId = menuDao.getFullMenu().size();
		Order order = menuDao.newOrder(request.getUserPrincipal().getName());

		for (int i = 0; i < maxId + 1; i++) {
			String quantity = request.getParameter("item_" + i);
			try {
				int q = Integer.parseInt(quantity);
				if (q > 0) {
					menuDao.addToOrder(order.getId(), menuDao.getItem(i), q);
					order.addToOrder(menuDao.getItem(i), q);
				}
			} catch (NumberFormatException nfe) {
				// that's fine it just means there wasn't an order for this item
			}
		}

		// Add new Order to handler that will display on kitchen management page ...
		// Asynchronous call
		KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandlerFactory.getHandler();
		handler.newOrder(order);
		
		// redirect to thank you page upon order reception
		HttpSession session = request.getSession();

		session.setAttribute("orderId", order.getId());

		String redirectURL = "/thankYou.html";
		redirectURL = response.encodeURL(redirectURL);
		response.sendRedirect(redirectURL);
	}
}
