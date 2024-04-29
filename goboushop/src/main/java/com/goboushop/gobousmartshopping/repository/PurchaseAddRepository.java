package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.Purchase;

@Repository
public interface PurchaseAddRepository extends JpaRepository<Purchase, Long> {
	
}
