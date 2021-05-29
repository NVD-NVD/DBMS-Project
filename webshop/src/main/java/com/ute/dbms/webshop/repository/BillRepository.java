package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.Bill;
import com.ute.dbms.webshop.entity.Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer> {
    Bill findById(int id);
    List<Detail> findAllDetailById(int id);
}
