package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUserID(Long userID);
}
