package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductPackaging;
import com.goboushop.gobousmartshopping.repository.ProductPackagingRepository;

@Service
public class ProductPackagingService {
	
	@Autowired
	ProductPackagingRepository repository;
	
	public void saveProductPackage(ProductPackaging prodpack) 
	{
		repository.save(prodpack);
	}
	
	public List<ProductPackaging> getAllProductPackages() {
		return repository.findAll();
	}
	
	public void deleteProdPackage(Long id) {
		repository.deleteById(id);
	}
}
