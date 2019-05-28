package com.yq.base.controller;

import com.yq.base.entity.Active;
import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import com.yq.base.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("active")
public class ArticleController {
    @Autowired
    private ActiveService activeService ;

    @GetMapping("findByActiveId/{id}")
    public Result findByActiveId(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功！",activeService.findActiveById(id));
    }

    @PostMapping()
    public Result addActive(@RequestBody Active active){
        activeService.addOrUpdateActive(active);
        return new Result(true, StatusCode.OK,"增加成功！");
    }

    @PutMapping()
    public Result updateActive(@RequestBody Active active){
        activeService.addOrUpdateActive(active);
        return new Result(true, StatusCode.OK,"修改成功！");
    }

    @DeleteMapping("{id}")
    public Result deleteActiveById(@PathVariable String id){
        activeService.deleteActiveById(id);
        return new Result(true, StatusCode.OK,"删除成功！");
    }
}
