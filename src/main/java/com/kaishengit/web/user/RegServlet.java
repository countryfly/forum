package com.kaishengit.web.user;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.UserService;
import com.kaishengit.web.BaseServlet;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/7.
 */
@WebServlet("/reg.do")
public class RegServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        foward(req,resp,"reg.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String patchca = req.getParameter("patchca");

        Map<String,Object> result = Maps.newHashMap();
        UserService userService = new UserService();

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(email) && StringUtils.isNotEmpty(patchca)){
            try{
                userService.createNewUser(username,password,email);
                result.put("state","success");
            }catch (ServiceException e){
                result.put("state","error");
                result.put("message","账号注册失败");
            }

        }else {
            result.put("state","error");
            result.put("message","账号注册失败");
        }

        rendJson(req,resp,result);

    }
}
