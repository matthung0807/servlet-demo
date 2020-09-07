package com.abc.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class DemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final static int MAX_SIZE_5MB = 5 * 1024 * 1024;
    
    public DemoServlet() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();

        if (StringUtils.isEmpty(pathInfo)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        if ("/upload".equalsIgnoreCase(pathInfo)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            servletFileUpload.setSizeMax(MAX_SIZE_5MB); // 5MB
            
            List<FileItem> itemList = null;
            try {
                itemList = servletFileUpload.parseRequest(request);
            } catch (FileUploadException e) {
                throw new ServletException("Cannot parse request to file");
            }
            
            for (FileItem item : itemList) {
                if (!item.isFormField()) {
                    String fileName = FilenameUtils.getName(item.getName());
                    String realPath = getServletContext().getRealPath("/upload");
                    String uploadFilePath = realPath + File.separator + fileName; // D:\\..\\apache-tomcat-9.0.37\\wtpwebapps\\servlet-demo\\upload\\duke.png
                    
                    InputStream is = item.getInputStream();
                    OutputStream os = new FileOutputStream(uploadFilePath);
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    
                    byte[] buffer = new byte[1024];
                    while (is.read(buffer) != -1) {
                        bos.write(buffer);
                    }
                    
                    bos.flush();
                    bos.close();
                    is.close();
                    
                }
            }
            
            return;
        }
        
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
    
}