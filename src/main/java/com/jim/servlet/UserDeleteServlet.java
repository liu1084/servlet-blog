package com.jim.servlet;

import com.google.gson.Gson;
import com.jim.entity.User;
import com.jim.reponse.Response;
import com.jim.service.IUser;
import com.jim.service.impl.UserImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by jim on 2017/7/29.
 * This class is ...
 */
@WebServlet(name = "userDelete", urlPatterns = "/userDelete")
public class UserDeleteServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				stringBuffer.append(line);
		} catch (Exception e) { /*report an error*/ }

		Gson gson = new Gson();
		String userIds = gson.fromJson(stringBuffer.toString(), String.class);
		
		if (StringUtils.isEmpty(userIds)) {
			Response.write(resp, 500, "user ids is empty");
			return;
		}

		String[] ids = userIds.split(",");
		IUser iUser = new UserImpl();
		for (String id : ids) {
			if (isUserIdExist(id)) {
				User user = iUser.findById(id);
				iUser.deleteUser(user);
			}
		}

		Response.write(resp, 200, "delete success");

	}


	private boolean isUserIdExist(String id) {
		boolean result = false;
		IUser iUser = new UserImpl();
		User user = iUser.findById(id);
		if (user != null && StringUtils.isNoneEmpty(user.getUsername())) {
			result = true;
		}

		return result;
	}

}
