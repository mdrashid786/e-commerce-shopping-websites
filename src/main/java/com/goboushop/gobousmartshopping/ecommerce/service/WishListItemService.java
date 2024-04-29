package com.goboushop.gobousmartshopping.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.ecommerce.beans.WishList;
import com.goboushop.gobousmartshopping.ecommerce.beans.WishListItems;
import com.goboushop.gobousmartshopping.ecommerce.repository.WishListItemsRepository;

@Service
public class WishListItemService {

	@Autowired
	WishListItemsRepository repository;
	
	public void addItem(WishListItems WishListItems) 
	{
		repository.save(WishListItems);
	}
	
	public List<WishListItems> getTotalItems(WishList wishList) {
	    return repository.findByWishListId(wishList);
	}
	
	public void deleteItem(Long id) {
		repository.deleteById(id);
	}
	
}
