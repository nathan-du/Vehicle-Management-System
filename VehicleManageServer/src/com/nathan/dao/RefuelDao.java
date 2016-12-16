package com.nathan.dao;

import com.nathan.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nathan on 16/4/8.
 */
public class RefuelDao {
    /**
     * add refuel
     * @param refuelDate
     * @param autoNum
     * @param num
     * @param typeOfOil
     * @param lofoil
     * @param priceperl
     * @param price
     * @return
     */
    public boolean addRefuel(String refuelNum, String refuelDate, String autoNum, String num, String typeOfOil, double lofoil, double priceperl, double price)
    {
        try
        {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into Refuel(refuelNum, refuelDate, autonum, num, typeOfOil, lofoil, priceperl, price) values('"+refuelNum+"', '"+refuelDate+"', '"+autoNum+"', '"+num+"', '"+typeOfOil+"', '"+lofoil+"', '"+priceperl+"', '"+price+"')";
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
}
