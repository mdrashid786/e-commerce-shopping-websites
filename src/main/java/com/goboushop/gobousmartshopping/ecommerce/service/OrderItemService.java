package com.goboushop.gobousmartshopping.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.ecommerce.beans.OrderItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.Orders;
import com.goboushop.gobousmartshopping.ecommerce.repository.OrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	OrderItemRepository repository;
	
	public void addItem(OrderItems orderItems) 
	{
		repository.save(orderItems);
	}
	
	public List<OrderItems> getTotalItems(Orders orders) {
	    return repository.findByOrdersId(orders);
	}
	
	public void deleteItem(Long id) {
		repository.deleteById(id);
	}
}
