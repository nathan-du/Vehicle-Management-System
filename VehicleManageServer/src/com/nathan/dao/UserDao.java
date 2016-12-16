package com.nathan.dao;

import com.nathan.DBConnection.DBConnection;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户注册、登陆判断功能
 * Created by Nathan on 16/3/29.
 */
public class UserDao {
    public UserDao()
    {
    }

    /**
     * login
     * @param username
     * @param password
     * @return authority
     */
    public String toLogin(String username, String password)
    {
        String returnContent = null;
        try {
            Connection conn = DBConnection.getConnection();
            String getpassword = "SELECT *from user where username = ?;";
            PreparedStatement psmt = conn.prepareStatement(getpassword);
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
            try {
                while (rs.next()) {
                    String s = rs.getString(3);
                    if (s.equals(password)) {
                        String name = rs.getString(2);
                        String authority = rs.getString(4);
                        String driver = rs.getString(5);
                        DBConnection.close(conn, psmt, rs);
                        returnContent = authority + "*" + name + "*" + username + "*" +driver;
                        return returnContent;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DBConnection.close(conn, psmt, rs);
        }
        catch (SQLException e)
        {
            System.err.println("无法连接到数据库");
            e.printStackTrace();
            System.exit(1);
        }
        return returnContent;

    }

    /**
     * sign in
     * @param username
     * @param name
     * @param password
     * @return
     */
    public String register(String username, String name, String password)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sqlcheck = "select count(*) from user where username = '"+username+"';";
            PreparedStatement psmt = conn.prepareStatement(sqlcheck);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                int judge = rs.getInt(1);// judge if it exists
                if(judge > 0)
                {
                    DBConnection.close(conn,psmt,rs);
                    return "same";
                }
            }
            String sql = "Insert into user(username, name, password, authority, driver) values ('"+username+"', '"+name+"', '"+password+"', 'user', '0');";
            psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt);
            return "true";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * change password
     * @param username
     * @param newPassword
     * @return
     */
    public boolean changePassword(String username, String oldPassword, String newPassword)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sqlcheck = "Select password from user where username = '"+username+"';";
            PreparedStatement psmt = conn.prepareStatement(sqlcheck);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                String judgePassword = rs.getString(1);
                if(!judgePassword.equals(oldPassword))
                {
                    DBConnection.close(conn, psmt, rs);
                    return false;
                }
            }
            String sql = "Update user set password = '"+newPassword+"' where username = '"+username+"';";
            psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt);
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * reset password
     * @param username
     * @return
     */
    public boolean resetPassword(String username)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "Update user set password = '000000' where username = '"+username+"';";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt);
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * select all users
     * @return
     */
    public String selectAllUser()
    {
        String returnContent = "";
        try{
            Connection conn = DBConnection.getConnection();
            String sql = "select username from user;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                returnContent += rs.getString(1) + "*";
            }
            return returnContent;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return returnContent;
        }
    }

    /**
     * select all user name who are not drivers
     * @return
     */
    public String selectAllNoDriver()
    {
        String returnContent = "";
        try{
            Connection conn = DBConnection.getConnection();
            String sql = "select username from user where driver = '0';";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                returnContent += rs.getString(1) + "*";
            }
            return returnContent;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return returnContent;
        }
    }

    /**
     * select all user name who are drivers
     * @return
     */
    public String selectAllDriver()
    {
        String returnContent = "";
        try{
            Connection conn = DBConnection.getConnection();
            String sql = "select username from user where driver = '1';";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                returnContent += rs.getString(1) + "*";
            }
            return returnContent;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return returnContent;
        }
    }
}
