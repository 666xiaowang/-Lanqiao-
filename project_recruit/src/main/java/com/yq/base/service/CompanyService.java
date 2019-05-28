package com.yq.base.service;

import com.yq.base.dao.CompanyDao;
import com.yq.base.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao ;

    //用户控制 是否热门：0  1
    public List<Company> popularList(String isPopular){
        return companyDao.findByIsPopular(isPopular) ;
    }
    //热门列表
    public List<Company> popularList(){
        return companyDao.findByIsPopular("1") ;
    }





}
