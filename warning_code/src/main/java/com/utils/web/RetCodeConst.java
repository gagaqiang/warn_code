package com.utils.web;

/**
 * WEB响应码常量
 */
public class RetCodeConst {

    public static final String RETCODE_INVAILD_SESSSION = "100";
    public static final String RETCODE_INVAILD_SESSSION_MSG = "会话失效，请重新登录";

    public static final String RETCODE_INVAILD_LOGIN = "101";
    public static final String RETCODE_INVAILD_LOGIN_MSG = "用户名或密码错误，请重新输入";

    public static final String RETCODE_INVAILD_PARAM_VAL = "102";
    public static final String RETCODE_INVAILD_PARAM_VAL_MSG = "接口参数异常，请检查";


    public static final String RETCODE_SUCCESS = "200";
    public static final String RETCODE_SUCCESS_MSG = "操作成功";

    public static final String RETCODE_ADD_SUCCESS = "201";
    public static final String RETCODE_ADD_SUCCESS_MSG = "新增成功";

    public static final String RETCODE_SET_SUCCESS = "202";
    public static final String RETCODE_SET_SUCCESS_MSG = "修改成功";

    public static final String RETCODE_DEL_SUCCESS = "203";
    public static final String RETCODE_DEL_SUCCESS_MSG = "删除成功";


    public static final String RETCODE_PERMISSION_DENIED = "403";
    public static final String RETCODE_PERMISSION_DENIED_MSG = "权限不足，请联系管理员开通";


    public static final String RETCODE_BUSINESS_ERROR = "600";
    public static final String RETCODE_BUSINESS_ERROR_MSG = "业务数据异常，请联系产品负责人";


    public static final String RETCODE_SERVER_ERROR = "500";
    public static final String RETCODE_SERVER_ERROR_MSG = "服务器内部异常，请联系系统管理员";

    public static final String RETCODE_DB_ERROR = "501";
    public static final String RETCODE_DB_ERROR_MSG = "数据库操作异常，请稍后再试或联系管理员";

    public static final String RETCODE_DB_CONNT_ERROR = "502";
    public static final String RETCODE_DB_CONNT_ERROR_MSG = "数据库连接异常，请稍后再试或联系管理员";


    public static final String RETCODE_IO_ERROR = "503";
    public static final String RETCODE_IO_ERROR_MSG = "保存文件出错，请联系系统管理员";

    public static final String RETCODE_DUBBO_ERROR = "505";
    public static final String RETCODE_DUBBO_ERROR_MSG = "服务远程调用失败";


    /**
     * 自定义默认消息返回码
     */
    public static final String RETCODE_CUSTOM = "300";
}
