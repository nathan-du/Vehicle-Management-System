package com.nathan.dao;

import com.nathan.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nathan on 16/4/2.
 */
public class DriverDao {
    public DriverDao()
    {

    }

    /**
     * request driver profile
     * @param username
     * @return
     */
    public String requestDriverProfile(String username)
    {
        String returnContent = null;
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "select *from driver where username = '"+username+"';";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                String idDriver = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String birthDate = rs.getString(4);
                String licenseDate = rs.getString(5);
                String fileNum = rs.getString(6);
                String type = rs.getString(7);
                String phoneNum = rs.getString(8);

                returnContent = idDriver + "*" + name + "*" + gender + "*" + birthDate + "*" + licenseDate + "*" + fileNum + "*" + type + "*" + phoneNum;
                return returnContent;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return returnContent;
        }

        return returnContent;
    }

    public String selectDriverName()
    {
        String returnContent = "";
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "select name from driver";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                returnContent += rs.getString(1) + "*";
            }
            return  returnContent;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return  returnContent;
        }
    }

    /**
     * add driver
     * @param idDriver
     * @param gender
     * @param birthdate
     * @param licensedate
     * @param filenum
     * @param type
     * @param phonenum
     * @return true or false
     */
    public boolean addDriver(String idDriver, String gender, String birthdate, String licensedate, String filenum, String type, String phonenum, String username)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String getName = "select name from user where username = '"+username+"';";
            PreparedStatement psmt = conn.prepareStatement(getName);
            ResultSet rs = psmt.executeQuery();
            String name = "";
            while(rs.next())
            {
                name = rs.getString(1);
            }
            String insertDriver = "insert into driver(idDriver, name, gender, birthdate, licensedate, filenum, type, phonenum, username) values('"+idDriver+"', '"+name+"', '"+gender+"', '"+birthdate+"', '"+licensedate+"', '"+filenum+"', '"+type+"', '"+phonenum+"', '"+username+"');";
            psmt = conn.prepareStatement(insertDriver);
            psmt.executeUpdate();
            String updateUserStatus = "update user set driver = '1' where username = '"+username+"';";
            psmt = conn.prepareStatement(updateUserStatus);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt, rs);
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * delete driver
     * @param username
     * @return
     */
    public boolean deleteDriver(String username)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "Delete from driver where username = '"+username+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            String sqlChange = "Update user set driver = '0' where username = '"+username+"';";
            psmt = conn.prepareStatement(sqlChange);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
