package com.service.demo;

import com.dto.weChat.WeChatResuDto;
import com.google.gson.Gson;

public class TestService {

    public static void main(String[] args){

        String pa = "{\"msg\":\"已发送\",\"result\":\"1\\n\",\"retCode\":\"200\"}";
        Gson gson = new Gson();

        WeChatResuDto we = gson.fromJson(pa, WeChatResuDto.class);
        System.out.println(we.getRetCode());
    }
}
