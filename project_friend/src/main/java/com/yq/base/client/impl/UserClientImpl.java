package com.yq.base.client.impl;

import com.yq.base.client.UserClient;
import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {


    @Override
    public Result updateFans(String userId, int number) {
        System.out.println("熔断器启动了..........");
        return new Result(false, StatusCode.ERROR, "远程调用出现，启动熔断保护....");

    }
}
