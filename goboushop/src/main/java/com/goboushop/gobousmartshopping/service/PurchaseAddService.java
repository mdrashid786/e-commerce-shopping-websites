package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.Purchase;
import com.goboushop.gobousmartshopping.repository.PurchaseAddRepository;

@Service
public class PurchaseAddService {
	@Autowired
	PurchaseAddRepository repository;
	
	public void savePurchase(Purchase purchase) 
	{
		repository.save(purchase);
	}
	
	public List<Purchase> getAllPurchase() {
		return repository.findAll();
	}
	
	public void deletePurchase(Long id) {
		repository.deleteById(id);
	}
}
