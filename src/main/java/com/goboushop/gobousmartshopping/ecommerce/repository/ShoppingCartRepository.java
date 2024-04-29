package com.goboushop.gobousmartshopping.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.ecommerce.beans.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	//this is for customer authentication
		@Query("SELECT u FROM ShoppingCart u WHERE u.user=?1")
	    ShoppingCart findByUserId(Users userId);
}
