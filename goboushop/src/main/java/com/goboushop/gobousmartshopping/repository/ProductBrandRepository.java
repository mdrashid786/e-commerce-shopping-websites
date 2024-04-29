package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

}
