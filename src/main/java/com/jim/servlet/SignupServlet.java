package com.jim.servlet;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.jim.utils.PasswordUtils.checkPassword;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
@WebServlet(name = "signUp", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=UTF-8");
		try {
			req.getRequestDispatcher("WEB-INF/signUp.jsp").forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");
		String email = req.getParameter("email").trim();

		if (checkForm(req, resp)) {

		}

	}

	private Boolean checkForm(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");
		String email = req.getParameter("email").trim();

		boolean result = false;
		List<String> errors = new ArrayList<>();

		if (StringUtils.isBlank(username)) {
			errors.add("username is include a white space or username is blank");
		}

		Pattern pattern = Pattern.compile("[a-z\u4e00-\u9fa5_]+");
		if (!pattern.matcher(username).matches()) {
			errors.add("username is invalid");
		}

		if (checkPassword(password) < 7) {
			errors.add("password is week");
		}

		if (!password.equals(rePassword)) {
			errors.add("password and rePassword is not matches");
		}

		pattern = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}\\b");
		if (!pattern.matcher(email).matches()) {
			errors.add("email is invalid");
		}

		if (errors.size() == 0) {
			result = true;
		}
		return result;
	}
}
