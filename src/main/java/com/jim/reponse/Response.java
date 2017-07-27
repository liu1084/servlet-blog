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
	private Message result;
	private int code;

	public Response(int c, Message m) {
		this.code = c;
		this.result = m;
	}

	public static String getResult(int code, Object msg) {
		Message m = new Message();
		m.setMsg(msg);

		Response response = new Response(code, m);
		Gson gson = new Gson();
		return gson.toJson(response);
	}

	public static void write(HttpServletResponse resp, int code, Object msg) {
		resp.setContentType("application/json; charset=UTF-8");
		resp.setStatus(code);
		PrintWriter printWriter = null;
		try {
			printWriter = resp.getWriter();
			printWriter.print(Response.getResult(code, msg));
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

	public Message getResult() {
		return this.result;
	}

	public void setResult(Message result) {
		this.result = result;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
