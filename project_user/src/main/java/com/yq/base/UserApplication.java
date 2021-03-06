package com.yq.base;

import com.yq.com.yq.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class UserApplication {
    public static void main(String[] args) throws IOException {
        //加密
//        BASE64Encoder encoder=new BASE64Encoder();
//        String abc = encoder.encode("zhangsan212".getBytes());
//        System.out.println(abc);
//        //注册：京东，   张三 ,abc
//
//        //解密
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] bytes = decoder.decodeBuffer("emhhbmdzYW4yMTI=");
//        System.out.println(new String(bytes)  );

         SpringApplication.run( UserApplication.class);
    }


    @Bean //IdWorker放入ioc容器 （1. @Bean +返回值  2.三层注解 ）
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
