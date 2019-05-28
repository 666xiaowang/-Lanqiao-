package com.yq.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//开启网关
@EnableZuulProxy
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args)  {

        SpringApplication.run( WebApplication.class);
    }
}
