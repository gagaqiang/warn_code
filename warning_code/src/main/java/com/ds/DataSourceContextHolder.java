package com.ds;

import java.util.ArrayList;
import java.util.List;

public class DataSourceContextHolder  {

    private static final ThreadLocal contextHolder = new ThreadLocal(); // 线程本地环境


    protected static final List<String> dataSourceIds = new ArrayList<String>();

    // 设置数据源类型
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    // 获取数据源类型
    public static String getDataSourceType() {
        return (String) contextHolder.get();
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
