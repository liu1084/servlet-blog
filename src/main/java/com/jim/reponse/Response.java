package com.jim.reponse;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jim on 2017/7/17.
 * This class is ...
 */
public class Response {
	private static Response response = null;

	private Response(int code, String msg) {
		Message message = new Message();
		message.setMsg(msg);
		Code code1 = new Code();
		code1.setCode(code);
	}

	public static String getResult(int code, String msg) {
		Response response = getInstance(code, msg);
		Gson gson = new Gson();
		return gson.toJson(response);
	}

	public static Response getInstance(int code, String message) {
		if (response == null) {
			response = new Response(code, message);
		}
		return response;
	}

	public static void write(HttpServletResponse resp, int code, String msg) throws IOException {
		resp.setContentType("application/json; charset=UTF-8");
		resp.setStatus(code);
		PrintWriter printWriter = resp.getWriter();
		printWriter.print(Response.getResult(code, msg));
		printWriter.flush();
		printWriter.close();
	}

	public static void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}
}
