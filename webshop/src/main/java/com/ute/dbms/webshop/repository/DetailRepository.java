package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {
}
