package com.yq.base.controller;

import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import com.yq.base.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recruit")
public class RecruitController {
        @Autowired
        private RecruitService recruitService;

        @GetMapping("query/recommendJob")
        public Result recommendJob(){
            return new Result(true, StatusCode.OK,"查询成功", recruitService.findTop10ByStateOrderByPublishTime("1"));
        }
}
