package com.kaishengit.web.user;

import com.kaishengit.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/8.
 */
@WebServlet("/validate/patchca.do")
public class ValidatePatchcaServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patchca = req.getParameter("patchca");

        HttpSession session = req.getSession();
        String getPatchca = (String) session.getAttribute("captcha");
        String result = null;

        if(getPatchca.equals(patchca)){
            result = "true";
        }else {
            result = "false";
        }

        rendText(resp,result);
    }
}
