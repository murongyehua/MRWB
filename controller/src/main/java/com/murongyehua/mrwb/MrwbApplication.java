package com.murongyehua.mrwb;

import com.murongyehua.mrwb.controller.config.WebConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author murongyehua
 */
@SpringBootApplication
@ComponentScan({"com.murongyehua.mrwb.base","com.murongyehua.mrwb.controller"})
@MapperScan({"com.murongyehua.mrwb.base.dao.mapper"})
@Import({WebConfig.class})
public class MrwbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrwbApplication.class, args);
    }

}
