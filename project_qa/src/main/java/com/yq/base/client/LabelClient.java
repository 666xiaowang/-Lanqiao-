package com.yq.base.client;

import com.yq.base.entity.PageResult;
import com.yq.base.entity.Result;
import com.yq.base.entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@FeignClient("project-base")
public interface LabelClient {
//    @RequestMapping(  method = RequestMethod.GET)
//    public Result findAll();
    @RequestMapping(value="/label/{id}",method = RequestMethod.GET)
    public   Result findLabelById(@PathVariable("id") String id);

//    @PostMapping
//    public Result saveLabel(@RequestBody Label label);
//    @RequestMapping(value="{id}" ,method = RequestMethod.PUT)
//    public Result updateLabel(@RequestBody Label label ,@PathVariable("id")String id);
//
//    @RequestMapping(value="{id}" ,method = RequestMethod.DELETE)
//    public Result deleteLabelById(@PathVariable("id")String id) ;
//
//
//    @GetMapping(value="findLabels" )
//    public Result findLabels(@RequestBody Map searchMap);
//
//    @GetMapping(value="findLabels/{start}/{pageSize}" ) //select *from xxx where ...?
//    public Result findLabels(@RequestBody Map searchMap, @PathVariable int start , @PathVariable int pageSize );
}
