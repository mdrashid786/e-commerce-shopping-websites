package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.Organisation;

@Repository
public interface organisationRepository extends JpaRepository<Organisation, Long>{
	
	// this is for neeraj
	@Query("from Organisation where id=:organisation_id")
	Organisation getImageByOrganisationId(Long organisation_id);
}
