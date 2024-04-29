package com.goboushop.gobousmartshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.goboushop.gobousmartshopping.beans.ProductAdd;
import com.goboushop.gobousmartshopping.beans.ProductSubCategory;

@Repository
public interface ProductAddRepository extends JpaRepository<ProductAdd, Long>{
	
}
