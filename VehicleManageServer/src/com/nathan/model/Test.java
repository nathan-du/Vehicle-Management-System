package com.nathan.model;


import java.util.ArrayList;

/**
 * Created by Nathan on 16/4/6.
 */
public class Test {
    public Test()
    {
        ArrayList<Auto> arrayList = new ArrayList<>();
        Auto auto = new Auto();
        auto.setAutoname("Sherrie");
        auto.setAutonum("ABC");
        auto.setPrice(9.5);
        auto.setPurchasedate("2016.4.6");
        auto.setStatus("1");
        auto.setTyeOfOil("93");
        arrayList.add(auto);
        String returnContent = "Auto:";
        for(int i = 0; i < arrayList.size(); i++)
        {
            returnContent += arrayList.get(i).getAutoname() + "*" + arrayList.get(i).getAutonum() + "*" + arrayList.get(i).getPrice() + "*" + arrayList.get(i).getPurchasedate() + "*" + arrayList.get(i).getStatus() + "*" + arrayList.get(i).getTyeOfOil() + "*";
        }
        System.out.println(returnContent);

    }

    public static void main(String[] args)
    {
        new Test();
    }
}
