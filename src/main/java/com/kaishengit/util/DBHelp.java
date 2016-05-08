package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DBHelp {
	public static void update(String sql,Object... params){
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new DataAccessException(e,"连接数据库错误");
        }

    }

    public static <T> T query(String sql,ResultSetHandler<T> handler,Object... params){
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            return queryRunner.query(sql,handler,params);
        } catch (SQLException e) {
            throw new DataAccessException(e,"连接数据库错误");
        }
    }

}
