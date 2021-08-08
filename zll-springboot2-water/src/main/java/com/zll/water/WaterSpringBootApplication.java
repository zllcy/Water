package com.zll.water;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * @MapperScan 会扫描到指定mapper包下，创建对应的实现类
 */
@MapperScan("com.zll.water.mapper")
public class WaterSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaterSpringBootApplication.class, args);
    }
}
