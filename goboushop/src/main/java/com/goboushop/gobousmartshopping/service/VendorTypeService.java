package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.VendorType;
import com.goboushop.gobousmartshopping.repository.VendorTypeRepository;

@Service
public class VendorTypeService {
	@Autowired
	VendorTypeRepository repository;
	
	public void saveProductBrand(VendorType vtype) 
	{
		repository.save(vtype);
	}
	
	public List<VendorType> getAllVendorTypes() {
		return repository.findAll();
	}
	
	public void deleteVType(Long id) {
		repository.deleteById(id);
	}
}
