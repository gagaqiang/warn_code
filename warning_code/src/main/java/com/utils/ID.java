package com.utils;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.UUID;

public class ID {
    private static final Logger logger = Logger.getLogger(ID.class);

    public static String getGuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static String getRandomId(){
        String str = DateUtil.fmtDateToStr(Calendar.getInstance().getTime(), "yyyyMMddHHmmss");
        return str;
    }


    public static void main(String[] args){
        System.out.println(getRandomId());
    }
}
