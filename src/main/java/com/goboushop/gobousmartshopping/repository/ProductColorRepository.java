package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductColor;
@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

}
