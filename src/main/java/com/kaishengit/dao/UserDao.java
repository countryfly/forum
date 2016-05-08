package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DBHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Administrator on 2016/5/7.
 */
public class UserDao {
    public void add(User user){
        String sql = "insert into t_user(username,password,email,createtime) values(?,?,?,?)";
        DBHelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getCreatetime());
    }

    public User findByName(String username){
        String sql = "select * from t_user where username = ?";
        return DBHelp.query(sql,new BeanHandler<User>(User.class),username);
    }

    public User findByEmail(String email){
        String sql = "select * from t_user where email = ?";
        return DBHelp.query(sql,new BeanHandler<User>(User.class),email);
    }

    public void update(User user){
        String sql = "update t_user set password = ?,avatar= ? , email= ? , loginip = ? , logintime = ? where id = ?";
    }

    public User findById(Integer id){
        String sql = "select * from t_user where id = ?";
        return DBHelp.query(sql,new BeanHandler<User>(User.class),id);
    }


}
