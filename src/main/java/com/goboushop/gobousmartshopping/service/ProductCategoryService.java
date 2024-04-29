package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductCategories;
import com.goboushop.gobousmartshopping.repository.ProductCategoriesRepository;

@Service
public class ProductCategoryService {
	@Autowired
	ProductCategoriesRepository repository;
	
	public void saveProductCategories(ProductCategories prodCat) 
	{
		repository.save(prodCat);
	}
	
	public List<ProductCategories> getAllProductCategories() {
		return repository.findAll();
	}
	
	public void deleteProdCategory(Long id) {
		repository.deleteById(id);
	}
	
}
