package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductTaxPercentage;

@Repository
public interface ProductTaxPercentageRepository extends JpaRepository<ProductTaxPercentage, Long> {

}
