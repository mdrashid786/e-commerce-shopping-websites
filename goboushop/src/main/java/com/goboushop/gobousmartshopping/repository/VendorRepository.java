package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	
}
