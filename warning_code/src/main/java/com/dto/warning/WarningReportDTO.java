package com.dto.warning;

public class WarningReportDTO {

    //运营天数 （xx天xx小时xx分）
    private String days;
    //总数
    private int yjTotal;
    //已处理
    private int dealTotal;
    //未处理
    private int undoTotal;
    //影响订单总量
    private int totalOrder;
    //已处理
    private int dealOrder;
    //未处理
    private int undoOrder;

    private String operUser;

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getYjTotal() {
        return yjTotal;
    }

    public void setYjTotal(int yjTotal) {
        this.yjTotal = yjTotal;
    }

    public int getDealTotal() {
        return dealTotal;
    }

    public void setDealTotal(int dealTotal) {
        this.dealTotal = dealTotal;
    }

    public int getUndoTotal() {
        return undoTotal;
    }

    public void setUndoTotal(int undoTotal) {
        this.undoTotal = undoTotal;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getDealOrder() {
        return dealOrder;
    }

    public void setDealOrder(int dealOrder) {
        this.dealOrder = dealOrder;
    }

    public int getUndoOrder() {
        return undoOrder;
    }

    public void setUndoOrder(int undoOrder) {
        this.undoOrder = undoOrder;
    }
}
