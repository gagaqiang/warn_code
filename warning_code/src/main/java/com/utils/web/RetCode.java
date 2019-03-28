package com.utils.web;

import java.io.Serializable;

/**
 * Web访问统一返回包装类
 */
public class RetCode<T> implements Serializable {
    private String retCode;
    private String msg;
    private T t;

    public RetCode() {
    }

    public RetCode(String retCode) {
        this(retCode, null, null);
    }

    public RetCode(String retCode, String retMsg) {
        this(retCode, retMsg, null);
    }

    public RetCode(String retCode, T t) {
        this(retCode, null, t);
    }

    public RetCode(String retCode, String msg, T t) {
        this.retCode = retCode;
        this.msg = msg;
        this.t = t;
    }

    /**
     * 操作成功
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> success() {
        return new RetCode<T>(RetCodeConst.RETCODE_SUCCESS, RetCodeConst.RETCODE_SUCCESS_MSG);
    }

    public static <T> RetCode<T> success(T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_SUCCESS, RetCodeConst.RETCODE_SUCCESS_MSG, t);
    }

    public static <T> RetCode<T> success(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_SUCCESS, msg, t);
    }


    /**
     * 新增成功<br>
     * 返回码{@link RetCodeConst#RETCODE_ADD_SUCCESS}<br>
     * 消息{@link RetCodeConst#RETCODE_ADD_SUCCESS_MSG}
     *
     * @return RetCode<T>
     */
    public static <T> RetCode<T> addSuccess() {
        return new RetCode<T>(RetCodeConst.RETCODE_ADD_SUCCESS, RetCodeConst.RETCODE_ADD_SUCCESS_MSG);
    }

    public static <T> RetCode<T> addSuccess(T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_ADD_SUCCESS, RetCodeConst.RETCODE_ADD_SUCCESS_MSG, t);
    }

    public static <T> RetCode<T> addSuccess(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_ADD_SUCCESS, msg, t);
    }


    /**
     * 修改成功<br>
     * 返回码{@link RetCodeConst#RETCODE_SET_SUCCESS}<br>
     * 消息{@link RetCodeConst#RETCODE_SET_SUCCESS_MSG}
     *
     * @return RetCode<T>
     */
    public static <T> RetCode<T> setSuccess() {
        return new RetCode<T>(RetCodeConst.RETCODE_SET_SUCCESS, RetCodeConst.RETCODE_SET_SUCCESS_MSG);
    }

    public static <T> RetCode<T> setSuccess(T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_SET_SUCCESS, RetCodeConst.RETCODE_SET_SUCCESS_MSG, t);
    }

    public static <T> RetCode<T> setSuccess(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_SET_SUCCESS, msg, t);
    }


    /**
     * 删除成功<br>
     * 返回码{@link RetCodeConst#RETCODE_DEL_SUCCESS}<br>
     * 消息{@link RetCodeConst#RETCODE_DEL_SUCCESS_MSG}
     *
     * @return RetCode<T>
     */
    public static <T> RetCode<T> delSuccess() {
        return new RetCode<T>(RetCodeConst.RETCODE_DEL_SUCCESS, RetCodeConst.RETCODE_DEL_SUCCESS_MSG);
    }

    public static <T> RetCode<T> delSuccess(T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_DEL_SUCCESS, RetCodeConst.RETCODE_DEL_SUCCESS_MSG, t);
    }

    public static <T> RetCode<T> delSuccess(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_DEL_SUCCESS, msg, t);
    }


    /**
     * 无效的参数
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> invaildParamVal() {
        return new RetCode<T>(RetCodeConst.RETCODE_INVAILD_PARAM_VAL, RetCodeConst.RETCODE_INVAILD_PARAM_VAL_MSG);
    }

    public static <T> RetCode<T> invaildParamVal(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_INVAILD_PARAM_VAL, msg);
    }

    public static <T> RetCode<T> invaildParamVal(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_INVAILD_PARAM_VAL, msg, t);
    }

    /**
     * 无效Session
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> invaildSession() {
        return new RetCode<T>(RetCodeConst.RETCODE_INVAILD_SESSSION, RetCodeConst.RETCODE_INVAILD_SESSSION_MSG);
    }

    /**
     * 没有权限
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> permissionDenied() {
        return new RetCode<T>(RetCodeConst.RETCODE_PERMISSION_DENIED, RetCodeConst.RETCODE_PERMISSION_DENIED_MSG);
    }

    public static <T> RetCode<T> permissionDenied(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_PERMISSION_DENIED, msg);
    }

    /**
     * 无效登录
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> invaildLogin() {
        return new RetCode<T>(RetCodeConst.RETCODE_INVAILD_LOGIN, RetCodeConst.RETCODE_INVAILD_LOGIN_MSG);
    }

    public static <T> RetCode<T> invaildLogin(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_INVAILD_LOGIN, msg);
    }

    /**
     * 系统错误
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> serverError() {
        return new RetCode<T>(RetCodeConst.RETCODE_SERVER_ERROR, RetCodeConst.RETCODE_SERVER_ERROR_MSG);
    }

    public static <T> RetCode<T> serverError(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_SERVER_ERROR, msg);
    }

    public static <T> RetCode<T> serverError(T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_SERVER_ERROR, t);
    }

    public static <T> RetCode<T> serverError(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_SERVER_ERROR, msg, t);
    }


    public static <T> RetCode<T> businessError() {
        return new RetCode<T>(RetCodeConst.RETCODE_BUSINESS_ERROR, RetCodeConst.RETCODE_BUSINESS_ERROR_MSG);
    }

    public static <T> RetCode<T> businessError(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_BUSINESS_ERROR, msg);
    }

    public static <T> RetCode<T> businessError(String msg, T t) {
        return new RetCode<T>(RetCodeConst.RETCODE_BUSINESS_ERROR, msg, t);
    }


    /**
     * 数据库操作异常
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> dbError() {
        return new RetCode<T>(RetCodeConst.RETCODE_DB_ERROR, RetCodeConst.RETCODE_DB_ERROR_MSG);
    }

    /**
     * 数据库连接异常
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> dbConntError() {
        return new RetCode<T>(RetCodeConst.RETCODE_DB_CONNT_ERROR, RetCodeConst.RETCODE_DB_CONNT_ERROR_MSG);
    }

    /**
     * IO异常
     *
     * @param <T>
     * @return
     */
    public static <T> RetCode<T> ioError() {
        return new RetCode<T>(RetCodeConst.RETCODE_IO_ERROR, RetCodeConst.RETCODE_IO_ERROR_MSG);
    }

    /**
     * 自定义消息返回<br>
     * 返回码{@link RetCodeConst#RETCODE_CUSTOM}
     *
     * @param msg 自定义消息
     * @return RetCode<T>
     */
    public static <T> RetCode<T> customMSG(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_CUSTOM, msg);
    }


    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return t;
    }

    public void setResult(T t) {
        this.t = t;
    }


    /*DUBBO远程调用失败*/
    public static <T> RetCode<T> dubboError() {
        return new RetCode<T>(RetCodeConst.RETCODE_DUBBO_ERROR, RetCodeConst.RETCODE_DUBBO_ERROR_MSG);
    }

    public static <T> RetCode<T> dubboError(String msg) {
        return new RetCode<T>(RetCodeConst.RETCODE_DUBBO_ERROR, msg);
    }


}
