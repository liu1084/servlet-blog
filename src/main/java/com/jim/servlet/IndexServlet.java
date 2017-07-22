package com.jim.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
@WebServlet
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

		res.setLocale(Locale.CHINA);
		Cookie cookie = new Cookie("name", "liujun");
		cookie.setSecure(true);
		cookie.setMaxAge(3600 * 2);
		cookie.setPath("/");
		res.addCookie(cookie);
		res.setHeader("Authorization", "Basic");


		try {
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, res);

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
