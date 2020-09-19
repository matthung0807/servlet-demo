package com.abc.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private int count = 0; // Request共用Servlet的成員變數，所以Servlet不應該設定成員變數。

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		count++;
		
		response.getWriter().append("count = " + count);
	}

}
