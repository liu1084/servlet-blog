package com.jim.servlet;


import com.jim.entity.User;
import com.jim.reponse.Response;
import com.jim.service.IUser;
import com.jim.service.impl.UserImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static com.jim.utils.PasswordUtils.checkPassword;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
@WebServlet(name = "signUp", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.getRequestDispatcher("WEB-INF/signUp.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		if (!(Boolean) checkForm(req).get("result")) {
			Response.write(resp, 500, checkForm(req).get("errors"));
			return;
		}

		IUser iUser = new UserImpl();
		User user = new User();
		String salt = UUID.randomUUID().toString();
		String pass = DigestUtils.md5Hex(password + salt);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(pass);

		User check = iUser.findByUsername(username);
		if (check.getId() != null && StringUtils.isNoneEmpty(check.getId().toString())) {
			Response.write(resp, 500, "username is existed");
			return;
		}


		check = iUser.findByEmail(email);

		if (check.getId() != null && StringUtils.isNoneEmpty(check.getId().toString())) {
			Response.write(resp, 500, "email is existed");
			return;
		}

		User u = iUser.add(user);

		if (StringUtils.isEmpty(u.getUsername())) {
			Response.write(resp, 500, "failed add");
			return;
		}

		Response.write(resp, 200, "success");

	}

	private Map<String, Object> checkForm(HttpServletRequest req) {
		String username = req.getParameter("username").trim();
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");
		String email = req.getParameter("email").trim();

		boolean result = false;
		Map<String, Object> map = new HashMap<>();
		List<String> errors = new ArrayList<>();

		if (StringUtils.isBlank(username)) {
			errors.add("username is include a white space or username is blank");
		}

		Pattern pattern = Pattern.compile("^[a-z\\u4e00-\\u9fa5_]+[a-z\\u4e00-\\u9fa5_0-9]*$");
		if (!pattern.matcher(username).matches()) {
			errors.add("username is invalid");
		}

		if (checkPassword(password) < 10) {
			errors.add("password is week");
		}

		if (!password.equals(rePassword)) {
			errors.add("password and rePassword is not matches");
		}

		pattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
		if (!pattern.matcher(email).matches()) {
			errors.add("email is invalid");
		}

		if (errors.size() == 0) {
			result = true;
		}

		map.put("result", result);
		map.put("errors", errors);

		return map;
	}
}
