package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(@Param("id") int id);
    List<Product> findAll();
}
