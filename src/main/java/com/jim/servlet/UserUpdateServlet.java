package com.jim.servlet;

import com.jim.entity.User;
import com.jim.reponse.Response;
import com.jim.service.IUser;
import com.jim.service.impl.UserImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jim on 2017/7/27.
 * This class is ...
 */
@WebServlet(name = "userUpdate", urlPatterns = "/userUpdate")
public class UserUpdateServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String userId = req.getQueryString().split("=")[1];
		IUser iUser = new UserImpl();
		User user = iUser.findById(userId);
		req.setAttribute("user", user);
		req.getRequestDispatcher("WEB-INF/userUpdate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String username = req.getParameter("username");
		String email = req.getParameter("email");

		IUser iUser = new UserImpl();
		User user = new User();
		user.setId(Integer.parseInt(userId));
		user.setUsername(username);
		user.setEmail(email);
		if (iUser.update(user) == 0) {
			Response.write(resp, 500, "update failed");
			return;
		}
		
		Response.write(resp, 200, "updated");
	}
}
