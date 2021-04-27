package com.ute.dbms.webshop.repository;


import com.ute.dbms.webshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);
}

