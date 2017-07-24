package com.jim.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by jim on 2017/7/24.
 * This class is ...
 */
@WebFilter
public class AuthorizationFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest) servletRequest);
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = req.getSession(false);
		String requestURI = req.getRequestURI();

		if ((session == null && !requestURI.endsWith("jsp")) || requestURI.endsWith("signIn")) {
			req.getRequestDispatcher("WEB-INF/signIn.jsp").forward(req, response);
			return;
		}

		filterChain.doFilter(req, response);
	}

	@Override
	public void destroy() {

	}
}
