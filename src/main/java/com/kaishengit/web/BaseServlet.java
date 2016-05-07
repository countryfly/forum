package com.kaishengit.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {
    public void foward(HttpServletRequest request, HttpServletResponse response,String viewName) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/"+viewName).forward(request,response);
    }
}
