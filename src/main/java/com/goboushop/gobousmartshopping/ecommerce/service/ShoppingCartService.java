
package com.goboushop.gobousmartshopping.ecommerce.service;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.ecommerce.beans.ShoppingCart;
import com.goboushop.gobousmartshopping.ecommerce.repository.ShoppingCartRepository;
  
@Service
public class ShoppingCartService {
	@Autowired
	ShoppingCartRepository repository;
	
	public void createUserShoppingCart(ShoppingCart shoppingCart) 
	{
		repository.save(shoppingCart);
	}
	
	//this is for getting user shopping cart
	public ShoppingCart getShoppingCartByUserId(Users userID)
	{
		return repository.findByUserId(userID);
	}
	
	public void deleteShoppingCart(Long id) {
		repository.deleteById(id);
	}
}
 