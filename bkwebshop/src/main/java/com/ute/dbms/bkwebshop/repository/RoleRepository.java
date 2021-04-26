package com.ute.dbms.bkwebshop.repository;

import com.ute.dbms.bkwebshop.entity.Role;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleName(@Param("rolename") String roleName);
}
