package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThankYouServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String total = req.getParameter("total");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><body><h1>Modern Kafe</h1>");
		out.println("<h2>Order your food</h2>");

		out.println("Thank you - your order has been received. You need to pay $" + total);

		out.println("</body></html>");
		out.close();
	}
	
}
