package com.dto.warning;

public class WarningReportStatisticsDto {
    //昨日新增条数
    private int yesAllWarnNum;
    //昨日总单量
    private int yesOrderNum;
    //昨日影响单量
    private int yesUndoWarnNum;
    //未处理条数
    private int undoWarnNum;
    //总条数
    private int allWarnNum;
    //受影响总单量-未处理
    private int undoAllOrders;


    public int getUndoAllOrders() {
        return undoAllOrders;
    }

    public void setUndoAllOrders(int undoAllOrders) {
        this.undoAllOrders = undoAllOrders;
    }

    public int getYesAllWarnNum() {
        return yesAllWarnNum;
    }

    public void setYesAllWarnNum(int yesAllWarnNum) {
        this.yesAllWarnNum = yesAllWarnNum;
    }

    public int getYesOrderNum() {
        return yesOrderNum;
    }

    public void setYesOrderNum(int yesOrderNum) {
        this.yesOrderNum = yesOrderNum;
    }

    public int getYesUndoWarnNum() {
        return yesUndoWarnNum;
    }

    public void setYesUndoWarnNum(int yesUndoWarnNum) {
        this.yesUndoWarnNum = yesUndoWarnNum;
    }

    public int getUndoWarnNum() {
        return undoWarnNum;
    }

    public void setUndoWarnNum(int undoWarnNum) {
        this.undoWarnNum = undoWarnNum;
    }

    public int getAllWarnNum() {
        return allWarnNum;
    }

    public void setAllWarnNum(int allWarnNum) {
        this.allWarnNum = allWarnNum;
    }
}
