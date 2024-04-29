package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.ProductCategories;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Long>{

}
