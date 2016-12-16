package com.nathan.net;

import com.nathan.dao.*;
import com.nathan.date.DateGet;
import com.nathan.model.Auto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nathan on 16/3/29.
 */
public class ServerThread extends Thread{
    Socket sc;
    byte[] buffer = new byte[200];

    public ServerThread(Socket sc)
    {
        this.sc = sc;
    }

    public void run() {
        InputStream in;
        try {
            in = sc.getInputStream();
            OutputStream out = sc.getOutputStream();
            while (true) {
                int count = in.read(buffer);
                String receiveContent = new String(buffer, 0, count);//receiving content
                System.out.println(receiveContent);
                String returnContent = parse(receiveContent);//parse the request
                out.write(returnContent.getBytes());
                ArrayList<Auto> auto = new ArrayList<>();

                if (receiveContent.equals("byebye")) break;
            }
            in.close();
            out.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parse the request
     * @param receiveContent
     * @return
     */
    public String parse(String receiveContent)
    {
        String method = receiveContent.substring(0,receiveContent.indexOf(":"));// get the specific request
        String content = receiveContent.substring(receiveContent.indexOf(":")+1);// get the parameter (details)
        String returnContent = null;
        if(method.equals("Log")) returnContent = log(content);// Log model and get the name, driver
        else if(method.equals("SignUp")) returnContent = register(content);// sign up
        else if(method.equals("ChangePassword")) returnContent = changePassword(content);// change password
        else if(method.equals("SelectAllDriver")) returnContent = selectAllDriver();//select all user names who are driver
        else if(method.equals("SelectAllNoDriver")) returnContent = selectAllNoDriver();//select all user names who are not driver
        else if(method.equals("SelectAllUser")) returnContent = selectAllUser();// select all username
        else if(method.equals("ResetPassword")) returnContent = resetPassword(content);// reset password
        else if(method.equals("SelectAutoNum")) returnContent = selectAutoNum();// select auto num which could be lent
        else if(method.equals("SelectAllAuto")) returnContent = selectAllAuto();// select data of all auto
        else if(method.equals("AddAuto")) returnContent = addAuto(content);// Add auto
        else if(method.equals("DeleteAuto")) returnContent = deleteAuto(content);// Delete auto
        else if(method.equals("RequestDriverProfile")) returnContent = requestDriverProfile(content);
        else if(method.equals("SelectDriverName")) returnContent = selectDriverName();//select all driver name
        else if(method.equals("AddDriver")) returnContent = addDriver(content);// Add Driver
        else if(method.equals("DeleteDriver")) returnContent = deleteDriver(content);// Delete Driver
        else if(method.equals("AddLendAutoRequest")) returnContent = addLendAutoRequest(content);// request to lend auto
        else if(method.equals("SelectLendAutoRequest")) returnContent = selectLendAutoRequest(content);// select lend auto request
        else if(method.equals("SelectAllLendAutoRequest")) returnContent = selectAllLendAutoRequest();// select all lend auto request
        //else if(method.equals("AgreeLendAutoRequest")) returnContent = commentLendAutoRequest(content);// comment the request
        //else if(method.equals("ReturnAuto")) returnContent = returnAuto(content);// user return auto *
        else if(method.equals("DeleteLendAuto")) returnContent = deleteLendAuto(content);// delete the record of lending auto
        else if(method.equals("ConfirmReturnAuto")) returnContent = confirmReturnAuto(content);// root confirm returning auto *TBD
        else if(method.equals("SelectOilCardNum")) returnContent = selectOilCardNum();//select all oil card number
        else if(method.equals("AddOilCard")) returnContent = addOilCard(content);// add oil card
        else if(method.equals("DeleteOilCard")) returnContent = deleteOilCard(content);// delete oil card
        else if(method.equals("ChangePasswordOilCard")) returnContent = changePasswordOilCard(content);// change the password of oil card
        else if(method.equals("ChangeBalance")) returnContent = changeBalance(content);// add or decline the balance of oil card
        else if(method.equals("AddRefuel")) returnContent = addRefuel(content);// add refuel
        else if(method.equals("AddRepair")) returnContent = addRepair(content);// add repair
        return returnContent;

    }

    /**
     * judge if password and username is right
     * @param content
     * @return authority
     */
    public String log(String content)
    {
        String[] temp = content.split("\\*");
        String username = temp[0];// get the username
        String password = temp[1];// get the password
        UserDao userDao = new UserDao();
        String returnContent = userDao.toLogin(username, password);
        return returnContent;
    }

    /**
     * sign in
     * @param content
     * @return
     */
    public String register(String content)
    {
        String[] temp = content.split("\\*");
        String username = temp[0];
        String name = temp[1];
        String password = temp[2];
        UserDao userDao = new UserDao();
        String judge = userDao.register(username, name, password);
        return judge;
    }

    /**
     * change password
     * @param content
     * @return
     */
    public String changePassword(String content)
    {
        String[] temp = content.split("\\*");
        String username = temp[0];
        String oldPassword = temp[1];
        String newPassword = temp[2];
        UserDao userDao = new UserDao();
        boolean judge = userDao.changePassword(username, oldPassword, newPassword);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * select all users who are driver
     * @return
     */
    public String selectAllDriver()
    {
        UserDao userDao = new UserDao();
        String returnContent = userDao.selectAllDriver();
        return returnContent;
    }

    /**
     * select all users who are not driver
     * @return
     */
    public String selectAllNoDriver()
    {
        UserDao userDao = new UserDao();
        String returnContent = userDao.selectAllNoDriver();
        return returnContent;
    }
    /**
     * select all user
     * @return
     */
    public String selectAllUser()
    {
        UserDao userDao = new UserDao();
        String returnContent = userDao.selectAllUser();
        return returnContent;
    }

    /**
     * reset password
     * @param content
     * @return
     */
    public String resetPassword(String content)
    {
        String[] temp = content.split("\\*");
        String username = temp[0];
        UserDao userDao = new UserDao();
        boolean judge =userDao.resetPassword(username);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * select all auto num
     * @return
     */
    public String selectAutoNum()
    {
        AutoDao autoDao = new AutoDao();
        String returnContent = autoDao.selectAutoNum();
        return returnContent;
    }

    /**
     * select all auto number
     * @return
     */
    public String selectAllAuto()
    {
        AutoDao autoDao = new AutoDao();
        String returnContent = autoDao.selectAllAuto();
        return returnContent;
    }

    /*
    public String selectAllAuto()
    {
        AutoDao autoDao = new AutoDao();
        ArrayList<Auto> arrayList = new ArrayList<>();
        arrayList = autoDao.selectAllAuto();
        String returnContent = "";
        for(int i = 0; i < arrayList.size(); i++)
        {
            returnContent += arrayList.get(i).getAutonum() + "*" + arrayList.get(i).getAutoname() + "*" +
                             arrayList.get(i).getPurchasedate() + "*" + arrayList.get(i).getPrice() + "*" +
                             arrayList.get(i).getTyeOfOil() + "*" + arrayList.get(i).getStatus() + "*";
        }
        return returnContent;
    }*/

    /**
     * add auto
     * @param content
     * @return when system adds auto, return true; Otherwise, return "false"
     */
    public String addAuto(String content)
    {
        String[] temp = content.split("\\*");
        String autonum = temp[0];// get the autonum
        String autoname = temp[1];// get the auto name
        String price = temp[2];// get the price
        String typeOfOil = temp[3];// get type of oil
        DateGet dateGet = new DateGet();
        String purchasedate = dateGet.getDate();// get the date of purchase
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.addAuto(autonum, autoname, purchasedate, price, typeOfOil);// adding auto
        if(judge == true)
        {
            return "true";
        }
            return "false";
    }

    /**
     * delete auto
     * @param content
     * @return return "true" or "false"
     */
    public String deleteAuto(String content)
    {
        String[] temp = content.split("\\*");
        String autonum = temp[0];// get the autonum
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.deleteAuto(autonum);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * request driver profile
     * @param content
     * @return
     */
    public String requestDriverProfile(String content)
    {
        String username = content;
        DriverDao driverDao = new DriverDao();
        String returnContent = driverDao.requestDriverProfile(username);
        return returnContent;
    }

    public String selectDriverName()
    {
        DriverDao driverDao = new DriverDao();
        String returnContent = driverDao.selectDriverName();
        System.out.println(returnContent);
        return  returnContent;
    }

    /**
     * add driver
     * @param content
     * @return "true" or "false"
     */
    public String addDriver(String content)
    {
        String[] temp = content.split("\\*");
        String idDriver = temp[0]; // get the id number of driver
        String gender = temp[1]; // get the gender of driver
        String birthDate = temp[2]; //get the birth date of driver
        String licenseDate = temp[3]; // the date of getting the license
        String fileNum = temp[4]; // get the file number of driver
        String type = temp[5]; // get the class
        String phoneNum = temp[6]; // get the phone number of driver
        String username = temp[7];
        DriverDao driverDao = new DriverDao();
        boolean judge = driverDao.addDriver(idDriver, gender, birthDate, licenseDate, fileNum, type, phoneNum, username);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * delelte driver
     * @param content
     * @return
     */
    public String deleteDriver(String content)
    {
        String[] temp = content.split("\\*");
        String idDriver = temp[0];
        DriverDao driverDao = new DriverDao();
        boolean judge = driverDao.deleteDriver(idDriver);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * record lending auto
     * @param content
     * @return
     */
    public String addLendAutoRequest(String content)
    {
        String[] temp = content.split("\\*");
        String autonum = temp[0];
        String username = temp[1];
        String driver =temp[2];
        String detail = temp[3];
        NumDao numDao = new NumDao();
        String num = numDao.lendAutoNum();
        String status = "1";
        DateGet dateGet = new DateGet();
        String usedate = dateGet.getDate();
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.addLendAuto(num, autonum, username, driver, usedate, detail, status);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     *
     * @param content
     * @return
     */
    public String selectLendAutoRequest(String content)
    {
        AutoDao autoDao = new AutoDao();
        String username = content;
        String returnContent = autoDao.selectLendAutoRequest(username);
        return returnContent;
    }

    public String selectAllLendAutoRequest()
    {
        AutoDao autoDao = new AutoDao();
        String returnContent = autoDao.selectAllLendAutoRequest();
        return returnContent;
    }

    /**
     *
     * @param content
     * @return
     */
    public String commentLendAutoRequest(String content)
    {
        String[] temp = content.split("\\*");
        String num = temp[0];
        String autonum = temp[1];
        String status = temp[2];//1 = agree 0 = disagree
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.commentLendAutoRequest(num, autonum, status);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }
    /**
     * delete the record of lending auto
     * @param content
     * @return
     */
    public String deleteLendAuto(String content)
    {
        String[] temp = content.split("\\*");
        String num = temp[0];
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.deleteLendAuto(num);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * user has returned auto
     * @param content
     * @return
     */
    public String returnAuto(String content)
    {
        String[] temp = content.split("\\*");
        String num = temp[0];
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.returnAuto(num);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     *root confirm auto has been returned
     * @param content
     * @return
     */
    public String confirmReturnAuto(String content)
    {
        String[] temp = content.split("\\*");
        String num = temp[0];
        String autonum = temp[1];
        AutoDao autoDao = new AutoDao();
        boolean judge = autoDao.confirmReturnAuto(num, autonum);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * select all oil card number
     * @return
     */
    public String selectOilCardNum()
    {
        OilCardDao oilCardDao = new OilCardDao();
        String returnContent = oilCardDao.selectOilCardNum();
        return returnContent;
    }

    /**
     * root adds oil card
     * @param content
     * @return
     */
    public String addOilCard(String content)
    {
        String[] temp = content.split("\\*");
        String idOilCard = temp[0];
        String password = temp[1];
        String balance = temp[2];
        OilCardDao oilCardDao = new OilCardDao();
        String judge = oilCardDao.addOilCard(idOilCard, password, balance);
        return judge;
    }

    /**
     * root deletes oil card
     * @param content
     * @return
     */
    public String deleteOilCard(String content)
    {
        String[] temp = content.split("\\*");
        String idOilCard = temp[0];
        OilCardDao oilCardDao = new OilCardDao();
        boolean judge = oilCardDao.deleteOilCard(idOilCard);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * root changes password
     * @param content
     * @return
     */
    public String changePasswordOilCard(String content)
    {
        String[] temp = content.split("\\*");
        String idOilCard = temp[0];
        String newPassword = temp[1];
        OilCardDao oilCardDao = new OilCardDao();
        boolean judge = oilCardDao.changePasswordOilCard(idOilCard, newPassword);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * change balance
     * @param content
     * @return
     */
    public String changeBalance(String content)
    {
        String[] temp = content.split("\\*");
        String idOilCard = temp[0];
        double balance = Double.parseDouble(temp[1]);
        OilCardDao oilCardDao = new OilCardDao();
        boolean judge = oilCardDao.changeBalance(idOilCard, balance);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    /**
     * add refuel
     * @param content
     * @return
     */
    public String addRefuel(String content)
    {
        String[] temp = content.split("\\*");
        DateGet dateGet = new DateGet();
        String refuelDate = dateGet.getDate();
        String autoNum = temp[0];
        String num = temp[1];
        String typeOfOil = temp[2];
        double lofoil = Double.parseDouble(temp[3]);
        double priceperl = Double.parseDouble(temp[4]);
        double price = Double.parseDouble(temp[5]);
        String refuelNum = temp[6];
        RefuelDao refuelDao = new RefuelDao();
        boolean judge = refuelDao.addRefuel(refuelNum, refuelDate, autoNum, num, typeOfOil, lofoil, priceperl, price);
        String judgeString = changeBalance(num + "*-" + price);
        if(judge == true && judgeString.equals("true"))
        {
            return "true";
        }
        return "false";

    }

    /**
     * add repair record
     * @param content
     * @return
     */
    public String addRepair(String content)
    {
        NumDao numDao = new NumDao();
        DateGet dateGet = new DateGet();
        String repairNum = numDao.repairNum();
        String[] temp = content.split("\\*");
        String autoNum = temp[0];
        String name = temp[1];
        String repairPart = temp[2];
        double repairFee = Double.parseDouble(temp[3]);
        String repairDate = dateGet.getDate();
        RepairDao repairDao = new RepairDao();
        boolean judge = repairDao.addRepair(repairNum, autoNum, repairDate, name, repairPart, repairFee);
        if(judge == true)
        {
            return "true";
        }
        return "false";
    }

    public static void main(String[] args)
    {
        Socket socket = new Socket();
        ServerThread serverThread = new ServerThread(socket);
        serverThread.changeBalance("123456789*-800");
    }
}
