package com.ute.dbms.bkwebshop.repository;

import com.ute.dbms.bkwebshop.entity.UserInfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
    
}
