package com.goboushop.gobousmartshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository repository;
	
	public Iterable<Users> getAllUsers() {
		return repository.findAll();
	}
	public Users getUserByUsernameAndPassword(Users users)
	{
		return repository.findByUsername(users.getUsername(), users.getPassword());
	}
	public Users getUserByUsernamePasswordAndCate(Users users)
	{
		return repository.findByUsernameAndCategory(users.getUsername(), users.getPassword(),users.getUsercategory().getId());
	}
	
}
