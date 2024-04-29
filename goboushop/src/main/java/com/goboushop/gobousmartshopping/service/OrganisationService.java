package com.goboushop.gobousmartshopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.repository.organisationRepository;

@Service
public class OrganisationService {
	@Autowired
	organisationRepository repository;
	
	public void saveOrganisation(Organisation org) 
	{
		repository.save(org);
	}
	
	public List<Organisation> getAllOrganisation() {
		return repository.findAll();
	}
	
	public Optional<Organisation> getImageById(Long id) {
		return repository.findById(id);
	}
	
	
	
	//for neeraj
	public Organisation getImageFormOrganisationId(Long id) {
		return repository.getImageByOrganisationId(id);
	}
	
	
	
}
