package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductCategories;
import com.goboushop.gobousmartshopping.beans.Vendor;

import com.goboushop.gobousmartshopping.repository.ProductCategoriesRepository;
import com.goboushop.gobousmartshopping.repository.VendorRepository;

@Service
public class VendorService {
	@Autowired
	VendorRepository repository;
	
	@Autowired
	ProductCategoriesRepository prodcatrepository;
	
	public void addvendorinproductCategories(Vendor vendor, List<Long> productCategoriesIds) {
	        if (vendor != null) {
	        	List<ProductCategories> productCategories = prodcatrepository.findAllById(productCategoriesIds);
	        	vendor.getProductCategories().addAll(productCategories);
	        	
	        	for(ProductCategories procat : productCategories ) {
	        		procat.getVendors().add(vendor);
	        	}
	        	repository.save(vendor);
	        }
	}
	
	public void saveProduct(Vendor vendor) 
	{
		repository.save(vendor);
	}
	
	public List<Vendor> getAllVendors() {
		return repository.findAll();
	}
	
	public void deleteVendor(Long id) {
		repository.deleteById(id);
	}
}
