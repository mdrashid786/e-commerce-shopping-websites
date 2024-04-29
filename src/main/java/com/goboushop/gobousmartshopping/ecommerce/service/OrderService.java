package com.goboushop.gobousmartshopping.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.ecommerce.beans.Orders;
import com.goboushop.gobousmartshopping.ecommerce.repository.OrdersRepository;

@Service
public class OrderService {
	@Autowired
	OrdersRepository repository;
	
	public void placeOrder(Orders orders) {
		repository.save(orders);
	}
	
	public List<Orders> getAllOrders() {
		return repository.findAll();
	}
	
	public List<Orders> getByUser(Users user) {
		return repository.findAllByUser(user);
	}
	
	@Transactional
    public void UpdateOrderStatus(Long orderId, String status) {
        Orders order = repository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        repository.save(order);
    }
}
