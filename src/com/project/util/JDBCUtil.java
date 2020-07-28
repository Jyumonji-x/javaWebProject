package com.project.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {
    public static void releaseConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static DataSource dataSource = null;
    static {
        dataSource = new ComboPooledDataSource("mvcApp");
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
