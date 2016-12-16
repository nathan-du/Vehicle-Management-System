package com.nathan.date;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Nathan on 16/4/3.
 */
public class DateGet {
    /**
     * get today date
     * @return
     */
    public String getDate() {
        Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy.MM.dd");
        return matter1.format(dt);
    }
}
