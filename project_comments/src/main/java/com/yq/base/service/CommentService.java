package com.yq.base.service;

import com.yq.base.dao.CommentDao;
import com.yq.base.entity.Comment;
import com.yq.com.yq.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;  //Spring JPA提供的统一接口。MySQL  、MongoDB

    //redisTemplate ;
    @Autowired
    MongoTemplate mongoTemplate ; //MongoDB自身特性

    @Autowired
    private IdWorker idWorker ;

    public List<Comment> findAll(){
        return commentDao.findAll() ;
    }

    public Comment findById(String id){
        return commentDao.findById(id).get() ;
    }



    //未测
    //增加评论时，给该评论所在的父节点+1 ：2次（自身评论：save，父节点：update）
    public void addComment(Comment comment){
        //sql -> mongodb
        //update comment set commentsCount=commentsCount+1 where
        comment.set_id(  String.valueOf( idWorker.nextId())  );

        //给该评论所在的父节点+1        null.方法()    null.属性() ->NullPointerException
        if(comment.getParentId()!=null  &&  !"".equals(comment.getParentId())){
            //父节点+1;
            //mongoDB自身的独有操作
            Query query = new Query();
            Criteria criteria = Criteria.where("_id").is(comment.getParentId());//where x = ?
            query.addCriteria(criteria ) ;
            Update update = new Update();
            update.inc("commentsCount",1);
            mongoTemplate.updateFirst(query,update,"comment") ;
        }
        commentDao.save(comment) ;//JPA统一接口
    }


    public void updateComment(Comment comment){
        commentDao.save(comment) ;
    }

    public void deleteCommentById(String id){
        commentDao.deleteById(id);
    }

    public Page<Comment> findByParentId( String parentId ,int start , int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return commentDao.findByParentId(parentId   , pageRequest ) ;
    }

    public void updateLikes(String id){
        //以下会访问两次数据库
//        Comment comment = commentDao.findById(id).get();
//        comment.setLikes( comment.getLikes() +1  );
//        commentDao.save(comment) ;
        //update xxx set xx=xx+1 where ...  -->mongobd语句
        //通过mongobd语句 将数据库访问次数减为1次

        /*
                1query： where....
                2update
                3:集合（表）
         */
        Query query = new Query() ;
        //创建查询条件 ...where _id = ?
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria) ; //mybatis逆向工程类似

        Update update = new Update();
        update.inc("likes",1);//update comment set likes = likes+1 ;
        mongoTemplate.updateFirst( query,update,"comment" )   ;

    }


}
