package com.yq.base.crawler.task;

import com.yq.base.crawler.ArticleDBPipeline;
import com.yq.base.crawler.processor.ArticleProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@Component
public class ArticleTask {

    @Autowired
    private ArticleProcessor articleProcessor;
    @Autowired
    private ArticleDBPipeline articleDBPipeline;

    @Autowired
    private RedisScheduler redisScheduler ;


    //1.放缓存     2.去重
    @Scheduled(cron ="0 34 * * * ?")
    public void task(){
        System.out.println("爬去处理...");
        Spider spider = Spider.create(articleProcessor);
        //文章列表的首页
        spider.addUrl("http://finance.sina.com.cn/roll/index.d.html?cid=230808");
        spider.addPipeline(articleDBPipeline) ;//articleDBPipeline：存入数据库
        spider.setScheduler(redisScheduler ) ;
        spider.start();
    }

}
