package com.yq.base.service;

import com.yq.base.dao.RecruitDao;
import com.yq.base.entity.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao ;

    public List<Recruit> findTop10ByStateOrderByPublishTime(String state){
        return recruitDao.findTop10ByStateOrderByPublishTimeDesc(state);
    }
}
