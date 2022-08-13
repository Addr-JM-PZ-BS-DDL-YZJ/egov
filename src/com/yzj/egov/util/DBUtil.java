package com.yzj.egov.util;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 数据库连接工具类
 */
public class DBUtil {

    private static  ResourceBundle bundle = ResourceBundle.getBundle("resources.db");
    private static  String driver = bundle.getString("driver");
    private static  String url = bundle.getString("url");
    private static  String user = bundle.getString("user");
    private static  String password = bundle.getString("password");


    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn==null){
            conn = DriverManager.getConnection(url,user,password);
            threadLocal.set(conn);
        }
       return  conn;

    }

    /**
     * 关闭连接
     * @param conn  连接对象
     * @param st    数据库操作对象
     * @param rs    返回结果集
     */
    public static void close(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                threadLocal.remove();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
