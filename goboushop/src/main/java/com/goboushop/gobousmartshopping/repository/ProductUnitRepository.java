package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductUnits;

@Repository
public interface ProductUnitRepository extends JpaRepository<ProductUnits, Long> {

}
