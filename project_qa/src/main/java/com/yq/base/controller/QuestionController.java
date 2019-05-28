package com.yq.base.controller;

import com.netflix.discovery.converters.Auto;
import com.yq.base.client.LabelClient;
import com.yq.base.entity.Question;
import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import com.yq.base.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private LabelClient labelClient ;

    @Autowired//@Resource
    private QuestionService questionService ;

    //跨服务调用
    @GetMapping(value="findLabelById/{labeid}")
    public Result findLabelById(@PathVariable String labeid){
        System.out.println("-------------Question....-------------");
        System.out.println(labelClient);
        return  labelClient.findLabelById(labeid);
    }



    @GetMapping(value="quesionlist/{labeid}/{start}/{pagesize}")
    public Result findNewQuestionsByLabelId(@PathVariable String labeid,@PathVariable int start,@PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findNewQuestionsByLabelId(labeid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }
    @GetMapping(value="hotquesionlist/{labeid}/{start}/{pagesize}")
    public Result findHotQuestionsByLabelId(@PathVariable String labeid,@PathVariable int start,@PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findHotQuestionsByLabelId(labeid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }

    @GetMapping(value="waitquesionlist/{labeid}/{start}/{pagesize}")
    public Result findWaitQuestionsByLabelId(@PathVariable String labeid,@PathVariable int start,@PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findWaitQuestionsByLabelId(labeid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }
}
