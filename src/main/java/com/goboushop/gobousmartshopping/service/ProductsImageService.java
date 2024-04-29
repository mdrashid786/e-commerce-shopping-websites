package com.goboushop.gobousmartshopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductImages;
import com.goboushop.gobousmartshopping.repository.ProductImageRepository;

@Service
public class ProductsImageService {
	@Autowired
	ProductImageRepository repository;
	
	public Optional<ProductImages> GetProductImageByProductIdAndDocName(Long productId, String docName) {
	    return repository.findByProductdoc_IdAndDocName(productId, docName);
	}
}
