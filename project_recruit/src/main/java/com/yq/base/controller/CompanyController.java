package com.yq.base.controller;

import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import com.yq.base.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService ;
    @GetMapping(value = "popularList/{isPopular}" )
    public Result popularList(@PathVariable String isPopular){
        return new Result(true, StatusCode.OK,"查询成功",companyService.popularList(isPopular));
    }

    @GetMapping(value = "popularList" )
    public Result popularList(){
        return new Result(true, StatusCode.OK,"查询成功",companyService.popularList());
    }
}
