package com.ute.dbms.webshop.repository;

import com.ute.dbms.webshop.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findAllByUserID(Long userID);
    Cart findByUserIDAndProductID(Long userID, int productID);
}
