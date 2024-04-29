package com.goboushop.gobousmartshopping.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductAdd;
import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.ecommerce.beans.CartItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.ShoppingCart;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {
	@Query("SELECT u FROM CartItems u WHERE u.shoppingCart=?1")
	List<CartItems> findByShoppingcartId(ShoppingCart shoppingCartId);
	
	//checking if product is added by user already
	boolean existsByShoppingCartAndProduct(ShoppingCart shoppingCart, ProductAdd product);
	CartItems findByShoppingCartAndProduct(ShoppingCart shoppingCart, ProductAdd product);
}
