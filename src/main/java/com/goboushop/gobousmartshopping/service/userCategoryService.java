package com.goboushop.gobousmartshopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.UsersCategory;
import com.goboushop.gobousmartshopping.repository.userCategoryRepository;

@Service
public class userCategoryService {
	@Autowired
	userCategoryRepository usercatrepository;
	
	public UsersCategory getUserById(Long id) {
		Optional<UsersCategory> optionalUser = usercatrepository.findById(id);
		return optionalUser.orElse(null);
	}
}
