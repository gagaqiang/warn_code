package com.ds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    public final static Logger _logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
    /**
     * @Before： 在方法执行之前进行执行：
     * @annotation(ds)：会拦截注解ds的方法，否则不拦截;
     * @param point
     * @param ds
     * @throws Exception
     */
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DS ds) throws Exception {
        //获取当前的指定的数据源;
        String dsId = ds.value();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DataSourceContextHolder.containsDataSource(dsId)) {
            //_logger.error("数据源[{}]不存在，使用默认数据源 > {}"+ds.value()+"------"+point.getSignature());
        } else {
            //_logger.error("Use DataSource : > "+ds.value()+"------"+point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DataSourceContextHolder.setDataSourceType(ds.value());
        }

    }



    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DS ds) {
        //_logger.error("Revert DataSource :> "+ds.value()+"-----"+point.getSignature());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DataSourceContextHolder.clearDataSourceType();

    }
}
