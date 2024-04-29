package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductColor;
import com.goboushop.gobousmartshopping.repository.ProductColorRepository;
@Service
public class ProductColorService {
	@Autowired
	ProductColorRepository repository;
	
	public void saveProductBrand(ProductColor prodcolor) 
	{
		repository.save(prodcolor);
	}
	
	public List<ProductColor> getAllProductColors() {
		return repository.findAll();
	}
	
	public void deleteProdColor(Long id) {
		repository.deleteById(id);
	}
}
