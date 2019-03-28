package com.enums;


import java.util.HashMap;
import java.util.Map;

public enum WarningEnum {

    WARNING_YW_CUS("订单推IMS失败！客户代码不匹配", "YJ00001"),
    WARNING_YW_CK("订单推IMS失败！大小仓不匹配", "YJ00002"),
    WARNING_YW_GOODS("订单推IMS失败！商品信息不匹配", "YJ00003"),
    WARNING_YW_KCDB("物流宝大仓跨仓调拨，请及时审核！", "YJ00004"),
    WARNING_YW_YCSH("异常售后-金卡剩余数不足", "YJ00016"),
    WARNING_YW_WULIUBAO("物流宝铺货预警", "YJ00027"),
    WARNING_YW_DIANPU_UNWEIHU("订单推IMS失败！店铺信息不匹配", "YJ00028"),
    WARNING_YW_ALIPAY_FLOWING("支付宝流水漏抓", "YJ00034"),
    WARNING_YW_WLB_MODEL("平台退货单商品信息和物流宝退货单商品信息不一致", "YJ00032"),
    WARNING_YW_WLB_DELIVERY_SYNCHRONIZATION("物流宝发货信息同步TC失败", "YJ00033"),
    WARNING_YW_REFUNDCARD_CATEGORY_OR_QTY_ERROR("退款单（种类、数量）错误", "YJ00036"),
    WARNING_YW_INTEGRAORDER_NOT_IMPORT("积分订单本月无导入预警", "YJ00038"),
    WARNING_YW_IINVOICE_15_TIMEOUT("已签收订单电子发票开票超15天未开预警", "YJ00039"),
    WARNING_YW_IINVOICE_30_TIMEOUT("已签收订单纸票开票超30天未开预警", "YJ00040"),
    WARNING_YW_MARGIN_REFUND("保证金退款", "YJ00048"),
    WARNING_YW_FLOWING_ACCOUNT("发生一笔客户转账支付，请手工入账", "YJ00049"),
    WARNING_YW_FLOWING_COLLECTION_TREASURE("发生一笔集分宝支付，请手工入账", "YJ00052"),
    WARNING_YW_MEMBER_UNMATCHED("派工协议配置中会员代码未匹配导致天猫供销订单拦截", "YJ00013"),
    WARNING_YW_OVER_6_DAYS("退款单（仅退款不退货）超6天未生成888发票号预警", "YJ00014"),
    WARNING_YW_SYNCHRONIZED_IMS_EXCEPTION("退货单处理后折扣转让信息同步给IMS异常", "YJ00015"),
    WARNING_YW_NO_MORE_THAN_45_DAYS("【订单管理-退货管理-异常退货】中超45天未审核预警", "YJ00017"),


    WARNING_YW_HUIZONG("业务消息统计", "YJ00007"),
    WARNING_IT_HUIZONG("IT消息统计", "YJ00008"),
    WARNING_ALL_HUIZONG("消息统计", "YJ00009"),


    WARNING_IT_FLOWING_TO_IMS_OUTTIME("流水入账,IMS超24小时未返回", "YJ00050"),
    WARNING_IT_FLOWING_TO_SAP_OUTTIME("流水入账,SAP超24小时未返回", "YJ00051"),
    WARNING_IT_FLOWING_TO_IMS_RETURN_ERROR("流水入账,IMS返回失败", "YJ00053"),
    WARNING_IT_FLOWING_TO_SAP_RETURN_ERROR("流水入账,SAP返回失败", "YJ00054"),


    WARNING_IT_RETURN_IMS_SLOW("退货单IMS超24小时未返回", "YJ00010"),
    WARNING_IT_RETURN_SAP_SLOW("退货单SAP超24小时未返回", "YJ00020"),
    WARNING_IT_RETURN_IMS_ERROR("退货单IMS返回失败", "YJ00011"),
    WARNING_IT_RETURN_SAP_ERROR ("退货单SAP返回失败", "YJ00012"),


    WARNING_IT_IINVOICE_AMOUNT_IS_ZERO("金额正常而开票金额为0", "YJ00035"),
    WARNING_IT_IINVOICE_HX_FAILED("航信开票失败预警", "YJ00041"),
    WARNING_IT_IINVOICE_ALI_FAILED("阿里开票失败预警", "YJ00043"),
    WARNING_IT_INVOICE_IMS_RETURNS_FAILED("发票接口，IMS返回失败", "YJ00046"),
    WARNING_IT_INVOICE_SAP_RETURNS_FAILED("发票接口，SAP返回失败", "YJ00047"),

    WARNING_IT_INVOICE_IMS_NO_RETURNS_OVER24HOURS("发票接口，IMS超24小时未返回", "YJ00045"),
    WARNING_IT_INVOICE_SAP_NO_RETURNS_OVER24HOURS("发票接口，SAP超24小时未返回", "YJ00042"),

    WARNING_IT_INVOICE_ALIORHX_TIMEOUT("阿里或者航信开票超24小时未返回", "YJ00044"),
    WARNING_IT_REFUNDCARD_NOT_AUTO_SET_OVER24HOURS("退款单正确，超24小时未自动结算", "YJ00037"),
    WARNING_IT_DD_IMS_SLOW("订单IT预警传输速度慢-订单IMS超时未返回", "YJ00019"),
    WARNING_IT_DD_SAP_SLOW("订单IT预警传输速度慢-订单SAP超时未返回", "YJ00018"),
    WARNING_IT_SMS("短信数量预警", "YJ00029"),
    WARNING_IT_TO_SAP_ERROR("订单SAP转入失败", "YJ00030"),
    WARNING_IT_TC_TO_IMS_SAP("TC传IMS正常，IMS未传SAP或SAP未返回", "YJ00031"),
    WARNING_IT_TC_JUSHITA1("聚石塔退货单漏抓","YJ00021"),
    WARNING_IT_TC_JUSHITA2("聚石塔订单漏抓","YJ00022");







    private String name;
    private String id;

    private WarningEnum(String name, String id) {
        this.name = name;
        this.id = id;
    }


    public static Map<String, String> getWarningMap(){
        Map<String, String> map = new HashMap<String, String>();
        for (WarningEnum e : WarningEnum.values()) {
            map.put( e.getId(), e.getName());
        }
        return map;
    }


    public static String getName(String id) {
        for (WarningEnum e : WarningEnum.values()) {
            if (e.getId().equals(id)) {
                return e.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


