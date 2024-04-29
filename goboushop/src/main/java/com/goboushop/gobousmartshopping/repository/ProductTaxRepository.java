package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductTax;

@Repository
public interface ProductTaxRepository extends JpaRepository<ProductTax, Long> {

}
