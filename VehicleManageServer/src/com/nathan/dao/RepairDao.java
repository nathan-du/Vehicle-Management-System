package com.nathan.dao;

import com.nathan.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nathan on 16/4/8.
 */
public class RepairDao {
    public boolean addRepair(String repairNum, String autoNum, String repairDate, String name , String repairPart, double repairFee)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into Repair(repairNum, autoNum, repairDate, name, repairPart, repairFee) values('"+repairNum+"', '"+autoNum+"', '"+repairDate+"', '"+name+"', '"+repairPart+"', '"+repairFee+"');";
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
}
