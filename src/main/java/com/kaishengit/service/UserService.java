package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.ConfigProp;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

/**
 * Created by Administrator on 2016/5/7.
 */
public class UserService {
    UserDao userDao = new UserDao();
    public void createNewUser(String username,String password,String email){
        if (userDao.findByName(username) != null){
            throw new ServiceException("账号已被注册");
        }
        if (userDao.findByEmail(email) != null){
            throw new ServiceException("邮箱已被注册");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(password+ConfigProp.get("user.password.salt")));
        user.setEmail(email);
        user.setAvatar(ConfigProp.get("user.default.avatar"));
        user.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

        userDao.add(user);
    }

    public User findByName(String username){
        return userDao.findByName(username);
    }

    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }

    public User login(String username,String password,String ip){
        User user = userDao.findByName(username);
        if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password+ConfigProp.get("user.password.salt")))){
            user.setLoginip(ip);
            user.setLogintime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            userDao.update(user);
            return user;
        }
        return null;
    }


}
