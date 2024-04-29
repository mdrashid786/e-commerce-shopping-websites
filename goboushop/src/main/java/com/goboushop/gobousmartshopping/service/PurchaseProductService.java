package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.PurchaseProduct;
import com.goboushop.gobousmartshopping.repository.PurchaseProductRepository;

@Service
public class PurchaseProductService {
	@Autowired
	PurchaseProductRepository repository;
	
	public void savePurchase(PurchaseProduct purchaseprod) 
	{
		repository.save(purchaseprod);
	}
	
	public List<PurchaseProduct> getAllPurchaseProduct() {
		return repository.findAll();
	}
	
	public void deletePurchaseProduct(Long id) {
		repository.deleteById(id);
	}
}
