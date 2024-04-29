package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductImages;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImages, Long> {

}
