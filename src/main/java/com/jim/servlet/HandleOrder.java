package com.jim.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jim on 2017/7/2.
 * This class is ...
 */
class HandleOrder implements Runnable {
	AsyncContext ctx;

	public HandleOrder(AsyncContext context) {
		this.ctx = context;
	}

	public void run() {
		ServletResponse response = this.ctx.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(10000);
			assert printWriter != null;
			printWriter.write("end");
			printWriter.flush();
			this.ctx.complete();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}
}
