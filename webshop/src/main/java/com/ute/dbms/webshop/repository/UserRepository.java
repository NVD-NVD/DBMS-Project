package com.ute.dbms.webshop.repository;


import com.ute.dbms.webshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    List<User> findAllByRoles(String rolename);
}

