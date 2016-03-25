package com.twixter.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {
	private static Properties config  = new Properties();
	
	static {
		try {
			InputStream inp = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
			config.load(inp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) servletRequest;
    	HttpServletResponse res = (HttpServletResponse) servletResponse;
    	
    	String authToken = req.getParameter("authToken");
    	if (!config.getProperty("web.authToken").equals(authToken)) {
    		res.setStatus(401);
    		res.getWriter().write("Unauthorized");
    		return;
    	}
    	else
    		filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig paramFilterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void destroy() {
        // do nothing
    }

}