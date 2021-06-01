package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(@Param("email") String email);
    List<User> findAll();
    List<User> findAllByRoles(@Param("Role") String role);
}
//query, update, select, delete, insert
