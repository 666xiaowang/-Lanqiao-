package com.yq.base.dao;

import com.yq.base.entity.Company;
import com.yq.base.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
//jpa:实现类
public interface RecruitDao  extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {
    //最新职位   order by xx  desc
    //..where state = ? order by publishTime
    public List<Recruit>  findTop10ByStateOrderByPublishTimeDesc(String state);
}
