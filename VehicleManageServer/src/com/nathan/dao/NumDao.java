package com.nathan.dao;


import com.nathan.DBConnection.DBConnection;
import com.nathan.date.DateGet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Nathan on 16/4/3.
 */
public class NumDao {
    /**
     * get lend auto number
     * @return lendAutoNum
     */
    public String lendAutoNum()
    {
        DateGet dateGet = new DateGet();
        String today = dateGet.getDate();//get today date
        String []temp = today.split("\\.");//get year month day
        char []yeartemp = temp[0].toCharArray();// to array
        String year = String.valueOf(yeartemp[2]) + String.valueOf(yeartemp[3]);// delete the first digital
        String todayinsql = year + temp[1] +temp[2];// get yymmdd
        String num = null; //database num, the last three digit of worknum
        String worknum = null;// the work number

        try
        {
            Connection conn = DBConnection.getConnection();
            String sql1 = "SELECT num from lendAutoNum where date='"+todayinsql+ "'";
            PreparedStatement psmt = conn.prepareStatement(sql1);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                num = rs.getString(1);
            }
            if(num == null)
            {
                String sql2 = "INSERT into lendAutoNum(date, num) values ('"+todayinsql+"','1');";
                psmt = conn.prepareStatement(sql2);
                psmt.executeUpdate();
                worknum = todayinsql+"001";
                DBConnection.close(conn, psmt, rs);
                return worknum;
            }
            else
            {
                String str = "000";
                int tempnum = Integer.valueOf(num).intValue() + 1;//add one to num
                String sql3 = "update lendAutoNum set num = '"+tempnum+"' where date = '"+todayinsql+"';";
                psmt = conn.prepareStatement(sql3);
                psmt.executeUpdate();
                String tempnumdone = String.valueOf(tempnum);// to String
                worknum = "1" + todayinsql + str.substring(0, 3-tempnumdone.length()) + tempnumdone;
                DBConnection.close(conn, psmt);
                return worknum;
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return worknum;
    }

    /**
     * get repair num
     * @return
     */
    public String repairNum()
    {
        DateGet dateGet = new DateGet();
        String today = dateGet.getDate();//get today date
        String []temp = today.split("\\.");//get year month day
        char []yeartemp = temp[0].toCharArray();// to array
        String year = String.valueOf(yeartemp[2]) + String.valueOf(yeartemp[3]);// delete the first digital
        String todayinsql = year + temp[1] +temp[2];// get yymmdd
        String num = null; //database num, the last three digit of worknum
        String repairnum = null;// the work number

        try
        {
            Connection conn = DBConnection.getConnection();
            String sql1 = "SELECT num from repairNum where date='"+todayinsql+ "'";
            PreparedStatement psmt = conn.prepareStatement(sql1);
            ResultSet rs = psmt.executeQuery();
            while(rs.next())
            {
                num = rs.getString(1);
            }
            if(num == null)
            {
                String sql2 = "INSERT into repairNum(date, num) values ('"+todayinsql+"','1');";
                psmt = conn.prepareStatement(sql2);
                psmt.executeUpdate();
                repairnum = todayinsql+"001";
                DBConnection.close(conn, psmt, rs);
                return repairnum;
            }
            else
            {
                String str = "000";
                int tempnum = Integer.valueOf(num).intValue() + 1;//add one to num
                String sql3 = "update repairNum set num = '"+tempnum+"' where date = '"+todayinsql+"';";
                psmt = conn.prepareStatement(sql3);
                psmt.executeUpdate();
                String tempnumdone = String.valueOf(tempnum);// to String
                repairnum = "1" + todayinsql + str.substring(0, 3-tempnumdone.length()) + tempnumdone;
                DBConnection.close(conn, psmt);
                return repairnum;
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return repairnum;
    }


    public static void main(String[] args)
    {

        System.out.println(new NumDao().lendAutoNum());
    }
}
