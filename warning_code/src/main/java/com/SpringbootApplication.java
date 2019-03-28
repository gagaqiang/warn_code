package com;

import com.ds.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAutoConfiguration(exclude={
        JpaRepositoriesAutoConfiguration.class
})
@SpringBootApplication
@MapperScan(basePackages = { "com.dao" })
@EnableScheduling
@EnableTransactionManagement
@Import({DynamicDataSourceRegister.class})
public class SpringbootApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringbootApplication.class, args);
    }
}




