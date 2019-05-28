package com.yq.base.dao;

import com.yq.base.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company,String> , JpaSpecificationExecutor<Company> {
    /*
        根据是否热门
     */
    public List<Company> findByIsPopular(String isHot) ;

}
