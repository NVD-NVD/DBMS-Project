package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Integer> {
    List<Detail> findAllById(int id);
}
