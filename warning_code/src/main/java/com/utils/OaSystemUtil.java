package com.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.aowsdl.ISysNotifyTodoWebServiceServiceLocator;
import com.utils.aowsdl.ISysNotifyTodoWebServiceServiceSoapBindingStub;
import com.utils.aowsdl.NotifyTodoAppResult;
import com.utils.aowsdl.NotifyTodoSendContext;
import com.utils.web.RetCode;
import org.apache.axis.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.*;

@Configuration
public class OaSystemUtil {

    @Value("${oa_link}")
    private String OA_LINK;

    private static final String TYPE = "2";   //1:表示审批类待办   2:表示为通知类待办
    private static final String MODELID = "WARNING_MODEL"; //标识待办在原系统唯一标识


    /**
     * oa消息发送
     * @param params
     *               字段       定义        是否必传     默认
     *             subject     标题          Y
     *             appName     待办来源       N         若为null,默认值等于:TC预警
     *             modelName   模块名         N          若为null,默认值等于:预警模块
     *             link        链接          N          若为null,默认值等于:http://10.88.2.139:8080/earlywarining/index.html#/main
     *
     * @param     targets     待办所属对象    Y          集合:{21314329,123214231}
     * @return
     */
    public  RetCode<String> sendMsgToAo(JSONObject params, List<String> targets){
        if(StringUtils.isEmpty(params.getString("subject"))){
            return RetCode.invaildParamVal("subject 不能为空");
        }

        if(StringUtils.isEmpty(params.getString("appName")))
            params.put("appName","TC预警");
        if(StringUtils.isEmpty(params.getString("modelName")))
            params.put("modelName","预警模块");

        if(StringUtils.isEmpty(params.getString("link")))
            params.put("link",OA_LINK);
            //params.put("link","http://tcwarning.aux-home.com/#/main");
        params.put("createTime",DateUtil.fmtDateToStr(new Date(),"yyyy-MM-dd HH:mm:ss"));
        params.put("type",TYPE);
        params.put("modelId",MODELID); 
        params.put("fdSendType", 1);
        Map<Object,String> map = null;
        if(targets.isEmpty()){
            return RetCode.invaildParamVal("targets不能为空");
        }else{
            JSONObject json = new JSONObject();
            for(int i=0;i<targets.size();i++){
                json.put("PersonNo",targets.get(i));
                params.put("targets",json.toString());
                boolean flag = sendMsgToAo(JSON.toJavaObject(params,NotifyTodoSendContext.class));
                if(!flag){
                    map = map == null ? new HashMap<>() : map;
                    map.put(i,targets.get(i));
                }
            }
        }
        if(map == null)
            return RetCode.success("已发送");
        return RetCode.businessError("发送失败:工号:" + JSON.toJSONString(map));
    }

    private static boolean sendMsgToAo(NotifyTodoSendContext context){
        NotifyTodoAppResult result = new NotifyTodoAppResult();
        try {
            java.net.URL endpointURL = new java.net.URL(Constants.AO_SEND_MSG_URL);
            ISysNotifyTodoWebServiceServiceSoapBindingStub ss = new ISysNotifyTodoWebServiceServiceSoapBindingStub(endpointURL,new ISysNotifyTodoWebServiceServiceLocator());
            result = ss.sendTodo(context);
            if (result.getReturnState() == 2)
                return true;
            result.setReturnState(1);
        } catch (RemoteException | MalformedURLException e) {
        }
        return false;
    }

    public static void main(String[] args) {
        JSONObject params = new JSONObject();
        params.put("subject","测试fdSendType 1111 ");
        List targets = new ArrayList();
        targets.add("170306011");
        targets.add("180625115");
        //System.out.println(JSONObject.toJSONString(sendMsgToAo(params, targets)));
    }

}
