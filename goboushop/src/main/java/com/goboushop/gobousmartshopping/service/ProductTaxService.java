package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductTax;
import com.goboushop.gobousmartshopping.repository.ProductTaxRepository;

@Service
public class ProductTaxService {
	@Autowired
	ProductTaxRepository repository;
	
	public void saveProductTax(ProductTax prodtax) 
	{
		repository.save(prodtax);
	}
	
	public List<ProductTax> getAllProductTax() {
		return repository.findAll();
	}
	
	public void deleteProdTax(Long id) {
		repository.deleteById(id);
	}
}
