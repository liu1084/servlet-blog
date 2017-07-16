package com.jim.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jim on 2017/7/2.
 * This class is ...
 */

@WebServlet(name = "order", urlPatterns = "/order", initParams = {@WebInitParam(name = "orderKey", value = "orderValue")}, asyncSupported = true)
public class OrderServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("start");
		printWriter.flush();

		AsyncContext asyncContext = req.startAsync();
		new Thread(new HandleOrder(asyncContext)).start();
	}
}
