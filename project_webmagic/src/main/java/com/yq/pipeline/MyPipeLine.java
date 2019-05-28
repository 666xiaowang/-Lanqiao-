package com.yq.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MyPipeLine implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
      String title =   resultItems.get("title") ;
        //mybatis  :insert into xx(title) values

        //插入数据库  :网站 title ->数据库
        System.out.println("自定义显示...."+title);
        //jdbc  mybatis

    }
}
