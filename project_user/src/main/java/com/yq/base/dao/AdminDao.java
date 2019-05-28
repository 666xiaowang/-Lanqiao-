package com.yq.base.dao;

import com.yq.base.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//DBUtil -> StudentDao  Class.forname    while(rs.next())
public interface AdminDao extends JpaRepository<Admin,String>, JpaSpecificationExecutor<Admin> {
    //根据名字找admin
    public Admin findByLoginName(String loginName) ;
}
