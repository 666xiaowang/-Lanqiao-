package com.yq.processor;

import com.yq.pipeline.MyPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.List;

//爬取类
public class MyProcessor  implements PageProcessor {


    /*
        https://www.sina.com.cn/
    @Override
    public void process(Page page) {

//        System.out.println(page.getHtml().toString() );
//        String content = page.getHtml().xpath("//*[@id='SI_Order_D']/div/div/div/span/a").toString();
//        System.out.println("---财经：" + content);

        List<String> links = page.getHtml().links().all();
        System.out.println("---超链接" + links);

    }
*/

    @Override
    public void process(Page page) {
        //https://finance.sina.com.cn/stock/stockzmt/2019-05-16/doc-ihvhiqax9034595.shtml
        //告知Spider要爬取内容的规则（“链接”）
        page.addTargetRequests(page.getHtml().links().regex("https://finance.sina.com.cn/stock/stockzmt/[0-9 -]+/doc-[a-z]{8}[0-9]{7}.shtml").all() );
        //爬取正文
        //从1开始计数  :
        // 获取http://finance.sina.com.cn/roll/index.d.html?cid=230808中第一个标题
//        System.out.println( "*************" +page.getHtml().xpath("//*[@id='Main']/div[3]/ul[1]/li[1]/a/text()") .toString()  );

//        System.out.println( "标题：" +page.getHtml().xpath("//*[@class='main-title']/text()") .toString()  );
//        System.out.println( "作者：" +page.getHtml().xpath("//*[@id='artibody']/p[1]/text()") .toString()  );
//        System.out.println( "正文：" +page.getHtml().xpath("//*[@id='artibody']/p[3]/text()") .toString()  );
        page.putField("title",page.getHtml().xpath("//*[@class='main-title']/text()") .toString());
    }

    @Override
    public Site getSite() {//sina.com
        //为了模拟人的行为：进入网站前 先休眠1秒
        return Site.me().setSleepTime(10).setRetrySleepTime(5);
    }

    public static void main(String[] args) {

//        Spider.create(new MyProcessor()).addUrl("http://finance.sina.com.cn/roll/index.d.html?cid=230808").run(); ;
        Spider.create(new MyProcessor())
                .addUrl("http://finance.sina.com.cn/roll/index.d.html?cid=230808")

                .addPipeline( new ConsolePipeline())  //控制台ConsolePipeline
                .addPipeline(new FilePipeline("d:\\dev\\sina.data"))  //文件FilePipeline
                .addPipeline(new JsonFilePipeline("d:\\dev\\sina.json"))  //json
                .addPipeline(new MyPipeLine()) //{k-v,k-v}


                .setScheduler( new QueueScheduler())//对内存去重
                .setScheduler(new FileCacheQueueScheduler("d:\\dev\\sina.data"))
//                .setScheduler(new RedisScheduler("192.168.2.129"))
                .run(); ;

    }



}
