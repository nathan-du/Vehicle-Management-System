package com.nathan.dao;

import com.nathan.DBConnection.DBConnection;
import com.nathan.model.Auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nathan on 16/4/1.
 */
public class AutoDao {
    public AutoDao()
    {

    }

    /**
     * 增加车辆模块
     * @param autonum
     * @param autoname
     * @param purchasedate
     * @param price
     * @return 成功为true，失败为false
     */
    public boolean addAuto(String autonum, String autoname, String purchasedate, String price, String typeOfOil)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sqlcheck = "select count(*) from auto where autonum = '"+autonum+"'";
            PreparedStatement psmt = conn.prepareStatement(sqlcheck);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                int judge = rs.getInt(1);//得到使用当前车牌号的数量
                if(judge > 0)
                {
                    DBConnection.close(conn,psmt,rs);
                    return false;
                }
            }
            String insertAuto = "insert into auto(autonum, autoname, purchasedate, price, typeOfOil, status) values('"+autonum+"','"+autoname+"','"+purchasedate+"', '"+price+"', '"+typeOfOil+"', '0');";
            psmt = conn.prepareStatement(insertAuto);
            psmt.executeUpdate();
            DBConnection.close(conn, psmt, rs);
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除车辆
     * @param autonum
     * @return 成功为ture，失败为false
     */
    public boolean deleteAuto(String autonum)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "Delete from auto where autonum = '"+autonum+"'";
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
     * 插入
     * @param autonum
     * @param username
     * @param driver
     * @param usedate
     * @param detail
     * @return
     */
    public boolean addLendAuto(String num, String autonum, String username, String driver, String usedate, String detail, String status)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into lendauto(num, autonum, username, driver, usedate, detail, status) values ('"+num+"','"+autonum+"', '"+username+"', '"+driver+"', '"+usedate+"', '"+detail+"', '"+status+"');";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            String sqlUpdate = "update auto set status = '"+status+"' where autonum = '"+autonum+"';";
            psmt = conn.prepareStatement(sqlUpdate);
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
     *
     * @param username
     * @return
     */
    public String selectLendAutoRequest(String username)
    {
        String returnContent = "";
        try
        {
            Connection conn = DBConnection.getConnection();
            String sqlRequest = "Select num, autonum, driver, usedate, detail from lendauto where username = '"+username+"' and status = '1' ;";
            PreparedStatement psmt = conn.prepareStatement(sqlRequest);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                String no = rs.getString(1);
                String autonum = rs.getString(2);
                String driver = rs.getString(3);
                String usedate = rs.getString(4);
                String detail = rs.getString(5);
                returnContent += no + "*" + autonum + "*" + driver + "*" + usedate + "*" + detail + "*";
            }
            if(returnContent.equals(""))
                return "null";
            return  returnContent;
        }
        catch (SQLException e)
        {
            return  returnContent;
        }
    }

    /**
     * select all lend auto request
     * @return
     */
    public String selectAllLendAutoRequest()
    {
        String returnContent = "";
        try
        {
            Connection conn = DBConnection.getConnection();
            String sqlRequest = "Select num, autonum, username, driver, usedate, detail from lendauto;";
            PreparedStatement psmt = conn.prepareStatement(sqlRequest);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                String no = rs.getString(1);
                String autonum = rs.getString(2);
                String username = rs.getString(3);
                String driver = rs.getString(4);
                String usedate = rs.getString(5);
                String detail = rs.getString(6);
                returnContent += no + "*" + autonum + "*" + username + "*" + driver + "*" + usedate + "*" + detail + "*";
            }
            if(returnContent.equals(""))
                return "null";
            System.out.println(returnContent);
            return  returnContent;
        }
        catch (SQLException e)
        {
            return  returnContent;
        }

    }

    /**
     * respond the lending auto request
     * @param num
     * @param autonum
     * @param status
     * @return
     */
    public boolean commentLendAutoRequest(String num, String autonum, String status)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            if(status.equals("1"))// agree the request
            {
                String sqlAuto = "update auto set status = '"+status+"' where autonum = '"+autonum+"';";
                PreparedStatement psmt = conn.prepareStatement(sqlAuto);
                psmt.executeUpdate();
                String sqlLendAuto = "update lendauto set status = '"+status+"' where num = '"+num+"';";
                psmt = conn.prepareStatement(sqlLendAuto);
                psmt.executeUpdate();
                DBConnection.close(conn, psmt);
                return true;
            }
            else if(status.equals("0"))// disagree the request
            {
                String sqlLendAuto = "update lendauto set status = '3' where num = '"+num+"';";
                PreparedStatement psmt = conn.prepareStatement(sqlLendAuto);
                psmt.executeUpdate();
                DBConnection.close(conn, psmt);
                return true;
            }
            return false;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * delete the record of lending auto
     * @param num
     * @return
     */
    public boolean deleteLendAuto(String num)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "Delete from lendauto where num = '"+num+"'";
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
     * user returns auto and change the valuse of STATUS in autolend table
     * @param num
     * @return
     */
    public boolean returnAuto(String num)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "update lendauto set status = '4' where num = '"+num+"';";
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
     * root ensures that the auto has been returned
     * @param num
     * @param autonum
     * @return
     */
    public boolean confirmReturnAuto(String num, String autonum)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "update lendauto set status = '0' where num = '"+num+"';";
            String sql1 = "update auto set status = '0' where autonum = '"+autonum+"';";
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
     * select Auto Number which could be lent
     * @return
     */
    public String selectAutoNum()
    {
        String returnContent = "";
        try{
            Connection conn = DBConnection.getConnection();
            String sql = "select autonum from auto where status = '0' ;";
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

    public String selectAllAuto()
    {
        String returnContent = "";
        try{
            Connection conn = DBConnection.getConnection();
            String sql = "select autonum from auto;";
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

    /*
    public ArrayList<Auto> selectAllAuto()
    {
        ArrayList<Auto> autoList = new ArrayList<>();
        Auto auto = new Auto();
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "select * from auto;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                auto.setAutonum(rs.getString(1));
                auto.setAutoname(rs.getString(2));
                auto.setPurchasedate(rs.getString(3));
                auto.setPrice(rs.getDouble(4));
                auto.setTyeOfOil(rs.getString(5));
                auto.setStatus(rs.getString(6));
                autoList.add(auto);
            }
            DBConnection.close(conn, psmt, rs);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return autoList;
    }*/

    public static void main(String[] args)
    {

    }
}
