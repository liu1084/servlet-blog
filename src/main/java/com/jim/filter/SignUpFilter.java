package com.jim.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by jim on 2017/7/26.
 * This class is ...
 */
public class SignUpFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
