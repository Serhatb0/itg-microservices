package com.itg.webornek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebOrnekApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebOrnekApplication.class, args);
    }

}
