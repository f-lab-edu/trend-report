package com.trendreport.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ExternalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalApplication.class,args);
    }
}
