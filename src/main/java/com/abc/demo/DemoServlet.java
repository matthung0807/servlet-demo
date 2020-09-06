package com.abc.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.abc.demo.dto.ApiResposne;
import com.abc.demo.dto.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public DemoServlet() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String pathInfo = request.getPathInfo();

        if (StringUtils.isEmpty(pathInfo)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        if ("/employee/add".equalsIgnoreCase(pathInfo)) {
            
            String body = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            EmployeeDto employeeDto = objectMapper.readValue(body, EmployeeDto.class);
            
            ApiResposne apiResposne = new ApiResposne("success", employeeDto);
            String jsonString = objectMapper.writeValueAsString(apiResposne);
            response.getWriter().write(jsonString);
            return;
        }
        
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    
}