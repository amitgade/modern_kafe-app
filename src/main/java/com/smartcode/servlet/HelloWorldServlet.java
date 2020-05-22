package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><bosy><h1>Hello World!</h1>");
		out.println("<p>The Time is : " + new Date()  + "<p>");
		out.println("</body></html>");
		out.close();
	}
}
