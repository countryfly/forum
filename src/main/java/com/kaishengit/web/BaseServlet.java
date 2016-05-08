package com.kaishengit.web;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseServlet extends HttpServlet {
    public void foward(HttpServletRequest request, HttpServletResponse response,String viewName) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/"+viewName).forward(request,response);
    }

    public void rendJson(HttpServletRequest request,HttpServletResponse response,Object result) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        printWriter.print(new Gson().toJson(result));

        printWriter.flush();
        printWriter.close();
    }

    public void rendText(HttpServletResponse response,String result) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(result);
        printWriter.flush();
        printWriter.close();
    }
}
