package com.jim.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
@WebServlet(name = "signUp", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			HttpSession session = req.getSession();
			session.removeAttribute("username");
			req.getRequestDispatcher("WEB-INF/signIn.jsp").forward(req, res);

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {

	}
}
