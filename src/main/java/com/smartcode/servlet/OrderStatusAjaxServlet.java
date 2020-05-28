package com.smartcode.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.smartcode.data.MenuDao;
import com.smartcode.data.MenuDaoFactory;

@WebServlet("/updatedStatus")
public class OrderStatusAjaxServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long orderId = Long.valueOf(request.getParameter("id"));

		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		String status = menuDao.getOrder(orderId).getStatus();

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		// normal way of sendig status
//		out.println(status);
		
		// json way
		JSONObject json = new JSONObject();
		json.put("status", status);
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		json.put("time", sdf.format(new Date()));
		out.write(json.toString());
		out.close();
	}
}
