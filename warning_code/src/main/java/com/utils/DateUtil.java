package com.utils;

import com.google.gson.Gson;
import gy.lib.common.util.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {


    /**
     * 获取当前时间之前、之后Num天数的日期
     *  num 日期往后增加一天.整数往后推,负数往前移动
     * @param num
     */
    public static String getDayTime(int num){
        Date date=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,num);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


    /**
     * 获取上个月份
     * @return
     */
    public static String getLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }

    /**
     * 时间转化字符串
     * @param time
     * @param str
     * @return
     */
    public static String fmtDateToStr(Date time, String str){
        SimpleDateFormat sf = new SimpleDateFormat(str);
        return sf.format(time);
    }



    /**
     * 分转化小时
     * @param mins
     * @return
     */
    public static String getHourAndMins(Integer mins){
        int hours = (int) Math.floor(mins / 60);
        int minute = mins % 60;
        String time = hours + "小时" + minute + "分钟";
        return time;
    }

    public static void getDate(Long oldTime){
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        /*long ns = 1000;// 一秒钟的毫秒数*/
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        // 获得两个时间的毫秒时间差异
        diff = new Date().getTime() - oldTime;
        day = diff / nd;// 计算差多少天
        hour = diff % nd / nh + day * 24;
        min = diff % nd % nh / nm + day * 24 * 60;
    }


    /**
     * 两个时间之间相差距离多少天
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static Long getDistanceDays(String str1, String str2) throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days=0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            
        }
        return days;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分
     * @param one 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分
     */
    public static String getDistanceTime2(Date one, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        //long sec = 0;
        try {
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            //sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {

        }
        return day + "天" + hour + "小时" + min + "分";
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTimeDate(Date one, Date two) {

        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (Exception e) {
            
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

        /**
         * 两个时间相差距离多少天多少小时多少分多少秒
         * @param str1 时间参数 1 格式：1990-01-01 12:00:00
         * @param str2 时间参数 2 格式：2009-01-01 12:00:00
         * @return String 返回值为：xx天xx小时xx分xx秒
         */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 获取2个时间相差的分钟数
     * @param endDate
     * @param nowDate
     * @return
     */
    public static Long getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        // 获得两个时间的毫秒时间差异
        long time1 = endDate.getTime();
        long time2 = nowDate.getTime();
        long diff = 0l;
        if(time1<time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;

        long mins = day * 24 * 60 + hour * 60 + min;

        return mins;

    }

    /**
     * 判断2个时间是否相同
     * @param time1
     * @param tagrtTime2
     * @param df
     * @return
     */
    public static boolean isCheckTrue(Date time1, String tagrtTime2, DateFormat df){
        return df.format(time1).equals(tagrtTime2);
    }


    /**
     * 判断目标是否在相差的时间范围内
     * @param tagrtTime
     * @param newDate
     * @param mins
     * @param df
     * @return
     */
    public static boolean isCheckTimesInMins(String tagrtTime, String newDate, int mins, DateFormat df) throws ParseException {
        //相差时间毫秒数
        long pas = mins * 60 * 1000;
        long taTimes = getHHMMtoTimes(tagrtTime);
        long orgTime = getHHMMtoTimes(newDate);
        if(orgTime > taTimes && orgTime - taTimes <= pas){
            return true;
        }
        return false;
    }



    public static Date toDate(String s) {
        if (StringUtil.isEmpty(s)) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (Exception e) {
            //logger.error(String.format("======%s=======日期转化错误", s), e);
        }
        return null;
    }

    /**
     * 获取当前时间前几天时间
     * @param days
     * @return
     */
    public static  String getTime(int days){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        String startTime = sdf.format(calendar.getTime());

        return startTime;
    }

    /**
     * HH:ss 转化为毫秒
     * @param ti
     * @return
     */
    public static long getHHMMtoTimes(String ti){
        long h = Integer.parseInt(ti.split(":")[0]) * 60 * 60 * 1000;
        long m = Integer.parseInt(ti.split(":")[1]) * 60 * 1000;
        return h+m;
    }



    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:HH");
        System.out.println(getDayTime(-1));
    }

}
