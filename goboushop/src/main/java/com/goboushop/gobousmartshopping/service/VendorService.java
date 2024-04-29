package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.Vendor;
import com.goboushop.gobousmartshopping.repository.VendorRepository;

@Service
public class VendorService {
	@Autowired
	VendorRepository repository;
	
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
