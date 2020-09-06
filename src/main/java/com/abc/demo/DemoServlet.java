package com.abc.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DemoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (StringUtils.isEmpty(pathInfo)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if ("/download".equalsIgnoreCase(pathInfo)) {
            response.setContentType("image/png");
            response.setHeader("Content-disposition", "attachment; filename=image.png");

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream("imgs/duke.png");
            OutputStream os = response.getOutputStream();
            
            byte[] buffer = new byte[1024];
            while ((is.read(buffer)) != -1) {
                os.write(buffer);
            }
            
            os.flush();
            os.close();
            is.close();
            
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

}