package com.yq.base.service;

import com.yq.base.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.yq.base.dao.QuestionDao;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public Page<Question> findNewQuestionsByLabelId(String labelId ,int start,int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return questionDao.findNewQuestionsByLabelId(  labelId , pageRequest);
    }

    public Page<Question>  findHotQuestionsByLabelId(String labelId ,int start,int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return questionDao.findHotQuestionsByLabelId(labelId,pageRequest);
    }


    public Page<Question>  findWaitQuestionsByLabelId(String labelId ,int start,int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return questionDao.findWaitQuestionsByLabelId(labelId,pageRequest);
    }
}
