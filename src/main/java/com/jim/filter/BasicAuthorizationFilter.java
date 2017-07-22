package com.jim.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

/**
 * Created by jim on 2017/7/23.
 * This class is ...
 */
public class BasicAuthorizationFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthorizationFilter.class);
	private String username;
	private String password;
	private String realm = "Protected";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.username = filterConfig.getInitParameter("username");
		this.password = filterConfig.getInitParameter("password");

		if (StringUtils.isNotEmpty(filterConfig.getInitParameter("realm"))) {
			this.realm = filterConfig.getInitParameter("realm");
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(authHeader);
			if (stringTokenizer.hasMoreTokens()) {
				String token = stringTokenizer.nextToken();
				if (token.equalsIgnoreCase("basic")) {
					String credentials = new String(Base64.getDecoder().decode(stringTokenizer.nextToken()));
					LOGGER.debug(credentials);
					int indexOf = credentials.indexOf(":");
					if (indexOf > -1) {
						String _username = credentials.substring(0, indexOf).trim();
						String _password = credentials.substring(indexOf + 1, credentials.length()).trim();

						if (!_username.equals(this.username) || !_password.equals(this.password)) {
							unAuthorization(response, "Bad username or password, unAuthorization!");
						} else {
							filterChain.doFilter(servletRequest, servletResponse);
						}
					} else {
						unAuthorization(response, "Bad username or password, unAuthorization!");
					}
				}
			}
		}
	}

	@Override
	public void destroy() {

	}

	private void unAuthorization(HttpServletResponse response, String message) throws IOException {
		response.setHeader("WWW-Authenticate", "Basic realm\"" + this.realm + "\"");
		response.sendError(401, message);
	}
}
