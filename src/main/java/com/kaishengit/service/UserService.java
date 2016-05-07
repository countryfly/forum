package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;

/**
 * Created by Administrator on 2016/5/7.
 */
public class UserService {
    UserDao userDao = new UserDao();

    public User findByName(String username){
        return userDao.findByName(username);
    }
}
