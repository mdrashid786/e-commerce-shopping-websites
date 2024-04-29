package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.PurchaseProduct;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long>{
	
}
