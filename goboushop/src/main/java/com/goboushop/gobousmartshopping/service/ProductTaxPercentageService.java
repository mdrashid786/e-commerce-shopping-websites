package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductTaxPercentage;
import com.goboushop.gobousmartshopping.repository.ProductTaxPercentageRepository;

@Service
public class ProductTaxPercentageService {
	@Autowired
	ProductTaxPercentageRepository repository;
	
	public void saveProductTax(ProductTaxPercentage prodtaxp) 
	{
		repository.save(prodtaxp);
	}
	
	public List<ProductTaxPercentage> getAllProductTaxp() {
		return repository.findAll();
	}
	
	public void deleteProdTaxp(Long id) {
		repository.deleteById(id);
	}
}
