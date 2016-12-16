package com.nathan.dao;

import com.nathan.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nathan on 16/4/7.
 */
public class OilCardDao {

    /**
     * root adds oil card
     * @param idOilCard
     * @param password
     * @param balance
     * @return
     */
    public String addOilCard(String idOilCard, String password, String balance)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sqlcheck = "select count(*) from oilCard where idOilCard = '"+idOilCard+"';";
            PreparedStatement psmt = conn.prepareStatement(sqlcheck);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                int judge = rs.getInt(1);//get the number of oil card used this id
                if(judge > 0)
                {
                    DBConnection.close(conn, psmt, rs);
                    return "same";
                }
            }
            String sql = "insert into OilCard(idOilCard, password, balance) values('"+idOilCard+"', '"+password+"', '"+balance+"')";
            psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt, rs);
            return "true";
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * root deletes oil card
     * @param idOilCard
     * @return
     */
    public boolean deleteOilCard(String idOilCard)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "Delete from OilCard where idOilCard = '"+idOilCard+"';";
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
     * root changes password of oil card
     * @param idOilCard
     * @param newPassword
     * @return
     */
    public boolean changePasswordOilCard(String idOilCard, String newPassword)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "update OilCard set password = '"+newPassword+"' where idOilCard = '"+idOilCard+"';";
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
     * root changes the balance of oil card
     * @param idOilCard
     * @param balance
     * @return
     */
    public boolean changeBalance(String idOilCard, double balance)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "update OilCard set balance = balance + '"+balance+"' where idOilCard = '"+idOilCard+"';";
            PreparedStatement psmt = conn.prepareStatement(sql);
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

    public String selectOilCardNum()
    {
        String returnContent = "";
        try{
            Connection conn = DBConnection.getConnection();
            String sql = "select idOilCard from OilCard;";
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

    public static void main(String args[])
    {
        new OilCardDao().changeBalance("123456789", -800);
    }
}
