package com.treat;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.treat.mapper"})
public class TreatMentAppliation {
    public static void main(String[] args) {
        SpringApplication.run(TreatMentAppliation.class, args);
    }
}
