package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartcode.data.MenuDataService;

public class OrderReceivedServlet extends HttpServlet {

	MenuDataService menuDataService = new MenuDataService();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int maxId = menuDataService.getFullMenu().size();
		for (int i = 0; i < maxId; i++) {
			String quantity = request.getParameter("item_" + i);
			try {
				int q = Integer.parseInt(quantity);
				if (q > 0)
					menuDataService.addToOrder(menuDataService.getItem(i), q);
			} catch (NumberFormatException nfe) {
				// that's fine it just means there wasn't an order for this item
			}
		}

		System.out.println("A new order has been received");

		Double total = menuDataService.getOrderTotal();
		
		response.sendRedirect("thankYou.html?total=" + total);
	}
}
