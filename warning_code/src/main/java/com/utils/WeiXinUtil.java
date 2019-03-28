package com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.enums.WxEnum;
import com.utils.web.RetCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WeiXinUtil {

    /**
     * 企业号消息发送  包含(纯文本消息,图文消息,多图文消息)
     * @param sendType
     * @param params
     * @desc
     *      ===>>纯文本消息
     *      sendType等于"TEXT"(使用姓名发送),  sendType等于"BTEXT"(使用工号发送)
     *          params对象必须包含参数:content,userIds,agentId
     *          示例:{"content":"hello world","userIds":["wuwenli","fuzheng"]}
     *      ===>>图文消息
     *      sendType等于"NEWS"(使用姓名发送),  sendType等于"BNEWS"(使用工号发送)
     *          params对象必须包含参数:title,desc,picUrl,url,userIds,agentId
     *          示例:{"userIds":["fuzheng","wuwenli"],"title":"奥克斯官网欢迎您！","desc":"去奥克斯官网看看吧","picUrl":"http://www.auxgroup.com/static/web/img/sy_03.jpg","url":"http://www.auxgroup.com"}
     *
     *      ===>>多图文消息
     *      sendType等于"MULNEWS"(使用姓名发送),  sendType等于"BMULNEWS"(使用工号发送)
     *          params对象必须包含参数:articles,title,desc,picUrl,url,userIds,agentId
     *          示例:{"userIds":["140408002"],"articles":[{"title":"关于xxx的年休假申请","desc":"","picUrl":"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png","url":"http://www.baidu.com"},{"title":"关于xxx的费用流程报销申请","desc":"","picUrl":"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png","url":"http://www.baidu.com"},{"title":"关于AAA的出差申请","desc":"","picUrl":"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png","url":"http://www.baidu.com"}]}
     * @return retCode = 200
     */
    public static RetCode<String> sendMsg(String sendType, JSONObject params){
        params.put("agentId","9");//微信企业号应用ID，固定值
        String url = null;
        String[] arg = null;
        switch (WxEnum.valueOf(sendType)) {
            case TEXT:
                url = Constants.WX_TEXT_URL;
                arg = new String[]{"content","userIds"};
                break;
            case BTEXT:
                url = Constants.WX_BTEXT_URL;
                arg = new String[]{"content","userIds"};
                break;
            case NEWS:
                url = Constants.WX_NEWS_URL;
                arg = new String[]{"title","desc","picUrl","url","userIds"};
                break;
            case BNEWS:
                url = Constants.WX_BNEWS_URL;
                arg = new String[]{"title","desc","picUrl","url","userIds"};
                break;
            case MULNEWS:
                url = Constants.WX_MULNEWS_URL;
                arg = new String[]{"articles","title","desc","picUrl","url","userIds"};
                break;
            case BMULNEWS:
                url = Constants.WX_BMULNEWS_URL;
                arg = new String[]{"articles","title","desc","picUrl","url","userIds"};
                break;
            default:
                return RetCode.invaildParamVal("不合法的请求!");
        }
        return checkParams(url,arg,params);
    }

    /**
     * 检查参数
     * @param arg
     * @param params
     * @return
     */
    private static RetCode<String> checkParams(String url,String[] arg,JSONObject params){
        for(String key : arg){
            if(!params.containsValue(key) && StringUtils.isEmpty(params.getString(key))){
                return RetCode.invaildParamVal("缺少参数:" + key + "或" + key + "不能为空字符");
            }
            if("userIds".equalsIgnoreCase(key)){
                params.put("userIds",params.getJSONArray("userIds"));
            }
        }
        return request(url,params);
    }

    /**
     * 发送请求
     * @param url
     * @param params
     * @return
     */
    private static RetCode<String> request(String url,JSONObject params){
        String result = HttpClient.post(url,params.toString(),Constants.DEFAULT_CONTENT_TYPE,Constants.REQUEST_METHOD, Constants.DEFAULT_CHARSET_UTF8);
        if ("1\n".equals(result))
            return RetCode.success("已发送",result);
        return RetCode.businessError(result);
    }
    public static void main(String[] args) {
        JSONObject params = new JSONObject();
        params.put("content","代码发送测试11111");
//        String[] userIds = new String[]{"chenjunjie5"};
//        String[] userIds = new String[]{"180625115","2232"};
//        params.put("userIds",userIds);

        List list = new ArrayList();
        list.add("180625115");
        list.add("2222");
        params.put("userIds",list);
        System.out.println(JSON.toJSONString(sendMsg("TEXT",params)));

        String componyCode = "xs1100";
        componyCode = componyCode.contains("xs")?componyCode.substring(2, 6):componyCode;
        System.out.println(componyCode);

//        JSONObject test = new JSONObject();
//        test.put("title","图文消息发送测试");
//        test.put("desc","图文消息发送测试");
//        test.put("picUrl","http://www.auxgroup.com/static/web/img/sy_03.jpg");
//        test.put("url","http://www.baidu.com");
//        test.put("userIds",userIds);
//        System.out.println(JSON.toJSONString(sendMsg("NEWS",test)));
//
//
//        JSONObject json = new JSONObject();
//        json.put("userIds",userIds);
//        JSONArray articles = new JSONArray();
//        for(int i=0;i<3;i++){
//            JSONObject s = new JSONObject();
//            s.put("title","图文消息发送测试");
//            s.put("desc","图文消息发送测试");
//            s.put("picUrl","http://www.auxgroup.com/static/web/img/sy_03.jpg");
//            s.put("url","http://www.baidu.com");
//            articles.add(s);
//        }
//        json.put("articles",articles);
//        System.out.println(JSON.toJSONString(sendMsg("MULNEWS",json)));
    }

    public static String test(){
//        String json = "{materialCode: '11151025000034', salesCenterCode: '182'}";
        JSONObject json = new JSONObject();
        json.put("materialCode","11151025000034");
        json.put("salesCenterCode","182");
        String url = "http://100.100.70.51:7004/cmsDispatcherServlet.ims?methodName=inventoryCanMunService";
        String result = HttpClient.post(url,JSON.toJSONString(json),Constants.DEFAULT_CONTENT_TYPE,Constants.REQUEST_METHOD, Constants.DEFAULT_CHARSET_UTF8);
        System.out.println(result);
        return null;
    }

}
