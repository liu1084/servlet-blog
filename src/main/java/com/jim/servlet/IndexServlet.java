package com.jim.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
@WebServlet(name = "index", urlPatterns = {"", "/"}, loadOnStartup = 1)
public class IndexServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexServlet.class);
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=UTF-8");
		Enumeration<String> requestHeader = req.getHeaderNames();

		while (requestHeader.hasMoreElements()) {
			String name = requestHeader.nextElement();
			LOGGER.debug(name + "------->" + req.getHeader(name));
		}

		//res.setHeader("Authorization", "Basic");


		try {
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, res);

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
