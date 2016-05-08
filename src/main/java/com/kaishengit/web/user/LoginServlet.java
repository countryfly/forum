package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.ConfigProp;
import com.kaishengit.web.BaseServlet;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/7.
 */
@WebServlet("/login.do")
public class LoginServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        foward(req,resp,"login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        Map<String,Object> result = Maps.newHashMap();
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){
            UserService userService = new UserService();
            User user = userService.login(username,password,req.getRemoteAddr());
            if (user != null){
                HttpSession session = req.getSession();
                session.setAttribute("curr_user",user);
                result.put("state","success");
            }else {
                result.put("state","error");
                result.put("message","账号或密码错误");
            }
        }else {
            result.put("state","error");
            result.put("message","参数错误");
        }

        rendJson(req,resp,result);
    }
}
