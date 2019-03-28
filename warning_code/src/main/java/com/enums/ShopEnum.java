package com.enums;


import java.util.HashMap;
import java.util.Map;

public enum ShopEnum {
    //AUX_JIADIAN_ZHUANMAIDIAN("奥克斯家电专卖店供应商",193248972),
    AUX_QIJIANDIAN_GONGYINGSHANG("奥克斯旗舰店供应商",  192883234),
    AUX_NB_ZHUANMAIDIAN("aux奥克斯宁波专卖店",  125568331),
    //AUX_CUNTAO_ZHUANJIA("村淘奥克斯(aux)空调商家",  76227698),
    AUX_WEIDIAN_SHOP("微店商城（电商)",  48698378),
    //AUX_PINPAISHANG("奥克斯品牌商",  34001),
    //AUX_CUNTAODIAN("村淘奥克斯店",  96977534),
    AUX_QIJIAN_SHOP("奥克斯旗舰店",  12181);

    private String name;
    private Integer id;

    private ShopEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }


    public static Map<Integer, String> getShopMap(){
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (ShopEnum e : ShopEnum.values()) {
            map.put( e.getId(), e.getName());
        }
        return map;
    }


    public static String getName(int id) {
        for (ShopEnum e : ShopEnum.values()) {
            if (e.getId() == id) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


