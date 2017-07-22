package com.jim.servlet;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by jim on 2017/6/30.
 * This class is ...
 */
@WebServlet(name = "login", urlPatterns = {"/login"},
		initParams = {@WebInitParam(name = "value", value = "1"), @WebInitParam(name = "key", value = "2")}, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("username") != null) {
			return;
		}

		res.setContentType("text/html;charset=UTF-8");
		res.setLocale(Locale.CHINA);
		res.setHeader("Authorization", "Basic");
		PrintWriter out = res.getWriter();
		String initValue = getServletConfig().getInitParameter("value");

		try {
			req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, res);

		} finally {
			out.close();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		LOGGER.info(username);
		Enumeration<String> names = req.getParameterNames();

		PrintWriter printWriter = resp.getWriter();
		Gson gson = new Gson();
		Map<String, List<String>> map = new HashMap();

		while (names.hasMoreElements()) {
			String parameterName = names.nextElement();
			String[] values = req.getParameterValues(parameterName);
			map.put(parameterName, Arrays.asList(values));
		}

		resp.setContentType("application/json; charset=UTF-8");
		LOGGER.info(gson.toJson(map));
		printWriter.write(gson.toJson(map));


//		String msg = "";
//		int code = 0;
//		IAuth iAuth = new AuthImpl();
//		if (!iAuth.checkUserExist(username)) {
//			msg = "username is not exist";
//			code = 500;
//		}
//
//		if (!iAuth.checkPasswordValid(username, password)) {
//			msg = "username or password is wrong";
//			code = 500;
//		}
//
//		if (code >= 500) {
//			Response.write(resp, code, msg);
//			req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
//		}
//
//		HttpSession session = req.getSession(true);
//
//		if (session.getAttribute("username") == null) {
//			Response.forwardToLogin(req, resp);
//		}
//
//
//		session.setAttribute("username", username);
//
//		if (StringUtils.isNotEmpty(req.getRequestURL().toString()) && !ArrayUtils.contains(req.getServletPath().split("/"), "login")) {
//			resp.sendRedirect(req.getRequestURL().toString());
//		} else {
//			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
//		}

	}
}
