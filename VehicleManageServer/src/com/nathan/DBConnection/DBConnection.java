package com.nathan.DBConnection;

import java.sql.*;

/**
 * 建立数据库链接
 * Created by Nathan on 16/3/29.
 */
public class DBConnection {
    static final String user = "root";
    static final String password = "";
    static final String url = "jdbc:mysql://localhost:3306/AutoManage";
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection conn, PreparedStatement psmt, ResultSet rs)
    {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if(conn != null)
            {
                conn.close();
            }
            if(psmt != null)
            {
                psmt.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, PreparedStatement psmt)
    {
        try{
            if(conn != null)
            {
                conn.close();
            }
            if(psmt != null)
            {
                psmt.close();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
