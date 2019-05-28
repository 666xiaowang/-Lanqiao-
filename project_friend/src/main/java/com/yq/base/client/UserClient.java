package com.yq.base.client;

import com.yq.base.client.impl.UserClientImpl;
import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

//Friend调用User项目的接口
//@Component
@FeignClient(value="project-user" ,fallback = UserClientImpl.class)
public interface UserClient {
    @PutMapping("user/updateFans/{userId}/{number}")
    public Result updateFans(@PathVariable("userId") String userId, @PathVariable("number") int number) ;
}
