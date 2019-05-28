package com.yq.base.crawler;


//import com.netflix.discovery.converters.Auto;
import com.yq.base.dao.ArticleDao;
import com.yq.base.entity.Article;
//import com.yq.com.yq.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ArticleDBPipeline implements Pipeline {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void process(ResultItems resultItems, Task task) {


        //爬去的内容->数据库
       String tile =  resultItems.get("title");
       String author =  resultItems.get("author");

        Article article = new Article();
        article.setId((int)(Math.random()*9000) + "");
        article.setTitle(tile);
        article.setDescription(author);//author

        articleDao.save( article);
    }
}
