package com.enums;

public enum WxEnum {
    /**
     * 纯文本-姓名
     */
    TEXT("TEXT"),
    /**
     * 纯文本-工号
     */
    BTEXT("BTEXT"),
    /**
     * 图文消息-姓名
     */
    NEWS("NEWS"),
    /**
     * 图文消息-工号
     */
    BNEWS("BNEWS"),
    /**
     * 多图文消息-姓名
     */
    MULNEWS("MULNEWS"),
    /**
     * 多图文消息-工号
     */
    BMULNEWS("BMULNEWS");

    private String sendType = "";

    WxEnum(String sendType) {
        this.sendType = sendType;
    }
}
