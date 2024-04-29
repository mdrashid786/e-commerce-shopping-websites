package com.goboushop.gobousmartshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductSubCategory;
@Repository
public interface ProductSubCategoyRepository extends JpaRepository<ProductSubCategory, Long> {
	 List<ProductSubCategory> findByCategoryId(Long categoryId);
}
