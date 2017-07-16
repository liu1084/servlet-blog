package com.jim.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jim on 2017/7/2.
 * This class is ...
 */
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String path = ((HttpServletRequest) servletRequest).getRequestURI();
		ServletContext context = servletRequest.getServletContext();
		if (path.startsWith(context.getContextPath() + "/order")) {

		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
