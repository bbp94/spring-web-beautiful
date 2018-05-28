package com.bxp.web.springweb.train;

/**
 * @Author: bbp(xiuping bao) on 2018/5/27.
 * @Date: 2018/5/27 10:47
 */
public class MyDate {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int miniter;

    public String arriveDate(String date,int addMin){
        year = Integer.valueOf(date.substring(0,4));
        month = Integer.valueOf(date.substring(4,6));
        day = Integer.valueOf(date.substring(6,8));
        hour = Integer.valueOf(date.substring(8,10));
        miniter = Integer.valueOf(date.substring(10));
        int temp = (miniter + addMin) / 60;
        miniter = (miniter + addMin) % 60;
        int hourTemp;
        hourTemp = (hour + temp) % 24;
        temp = (hour + temp) / 24;
        int dayTemp;
        dayTemp = (day + temp) % 30;
        temp = (day + temp) / 30;
        int monthTemp;
        monthTemp = (month + temp) % 12;
        temp = (month + temp) /12;
        year = year + temp;
        String hTemp = String.valueOf(hourTemp);
        String minTemp = String.valueOf(miniter);
        String monTemp = String.valueOf(monthTemp);
        String dTemp = String.valueOf(dayTemp);
        if(hourTemp<10){
            hTemp = "0"+hourTemp;
        }
        if (miniter<10){
            minTemp = "0"+miniter;
        }
        if (monthTemp<10){
            monTemp = "0"+monthTemp;
        }
        if (dayTemp<10){
            dTemp = "0"+dayTemp;
        }

        return year+"."+monTemp+"."+dTemp+" "+hTemp+":"+minTemp;

    }
}
