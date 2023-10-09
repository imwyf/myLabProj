package com.imwyf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.创建Main类,并声明这是一个主程序类也是个SpringBoot应用
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        //3.开始启动主程序类
        SpringApplication.run(Main.class,args);
    }
}
