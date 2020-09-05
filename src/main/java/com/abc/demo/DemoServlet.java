package com.abc.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.abc.demo.dto.ApiResposne;
import com.abc.demo.dto.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        String path = pathInfo.split("/")[1]; // e.g.: /employee

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        PrintWriter writer = response.getWriter();

        if ("employee".equalsIgnoreCase(path)) {
            EmployeeDto employeeDto = new EmployeeDto(1L, "john", "john@abc.com", 28);
            ApiResposne apiResposne = new ApiResposne("success", employeeDto);
            jsonString = objectMapper.writeValueAsString(apiResposne);
            writer.write(jsonString);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        writer.write(jsonString);
        return;
    }

}