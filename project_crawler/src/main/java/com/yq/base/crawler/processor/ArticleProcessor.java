package com.yq.base.crawler.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
@Component
public class ArticleProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //告知Spider要爬取内容的规则（“链接”）
        page.addTargetRequests(page.getHtml().links().regex("https://finance.sina.com.cn/stock/stockzmt/[0-9 -]+/doc-[a-z]{8}[0-9]{7}.shtml").all() );
        //爬取正文
        //从1开始计数  :
        // 获取http://finance.sina.com.cn/roll/index.d.html?cid=230808中第一个标题
//        System.out.println( "*************" +page.getHtml().xpath("//*[@id='Main']/div[3]/ul[1]/li[1]/a/text()") .toString()  );
        String title = page.getHtml().xpath("//*[@class='main-title']/text()") .toString()  ;
        String author = page.getHtml().xpath("//*[@id='artibody']/p[1]/text()") .toString()  ;
        page.putField("title",title);
        page.putField("author",author);

    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(10).setRetrySleepTime(5);
    }
}
