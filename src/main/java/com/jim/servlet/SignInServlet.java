package com.jim.servlet;

import com.jim.constant.ServletConstant;
import com.jim.service.IAuth;
import com.jim.service.impl.AuthImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by jim on 2017/6/30.
 * This class is ...
 */
@WebServlet(name = "signIn", urlPatterns = {"/signIn"}, loadOnStartup = 1)
public class SignInServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignInServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("username") != null && (Boolean) session.getAttribute(ServletConstant.HAS_LOGIN.name())) {
			res.sendRedirect(req.getRequestURL().toString());
		}
		req.getRequestDispatcher("WEB-INF/signIn.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		LOGGER.info(username);


		String msg = "";
		int code = 0;
		IAuth iAuth = new AuthImpl();
		if (!iAuth.checkUserExist(username)) {
			msg = "username is not exist";
			code = 500;
		}

		if (!iAuth.checkPasswordValid(username, password)) {
			msg = "username or password is wrong";
			code = 500;
		}

		if (code >= 500) {
			resp.setHeader("code", Integer.toString(code));
			resp.setHeader("message", msg);
			req.getRequestDispatcher("WEB-INF/signIn.jsp").forward(req, resp);
			return;
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("username", username);

		if (StringUtils.isNotEmpty(req.getRequestURL().toString()) && !ArrayUtils.contains(req.getServletPath().split("/"), "signIn")) {
			resp.sendRedirect(req.getRequestURL().toString());
		} else {
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		}

	}
}
