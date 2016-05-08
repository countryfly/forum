package com.kaishengit.web.user;

import com.kaishengit.service.UserService;
import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/8.
 */
@WebServlet("/validate/email.do")
public class ValidateEmailServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        UserService userService = new UserService();
        String result = null;
        if(userService.findByEmail(email) == null){
            result = "true";
        }else {
            result = "false";
        }

        rendText(resp,result);
    }
}
