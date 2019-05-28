package com.yq.base.dao;


import com.yq.base.entity.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ActiveDao extends JpaRepository<Active,String>, JpaSpecificationExecutor<Active> {
}