package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

}

