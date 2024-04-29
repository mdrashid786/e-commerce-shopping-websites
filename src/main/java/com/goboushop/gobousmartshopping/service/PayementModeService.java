package com.goboushop.gobousmartshopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.PayementMode;
import com.goboushop.gobousmartshopping.repository.PayementModeRepository;

@Service
public class PayementModeService {
	@Autowired
	PayementModeRepository repository;
	
	public void saveProductPayemode(PayementMode pMode) 
	{
		repository.save(pMode);
	}
	
	public List<PayementMode> getAllProductPMode() {
		return repository.findAll();
	}
	
	public void deleteProdPmode(Long id) {
		repository.deleteById(id);
	}
}
