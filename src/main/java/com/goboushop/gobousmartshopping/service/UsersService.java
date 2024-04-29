package com.goboushop.gobousmartshopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goboushop.gobousmartshopping.beans.ProductAdd;
import com.goboushop.gobousmartshopping.beans.Purchase;
import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository repository;
	
	public void saveUser(Users users) 
	{
		repository.save(users);
	}
	
	
	public Iterable<Users> getAllUsers() {
		return repository.findAll();
	}
	
	public Users getUserByUsernameAndPassword(Users users)
	{
		return repository.findByUsername(users.getUsername(), users.getPassword());
	}
	
	//this is for admin
	public Users getUserByUsernamePasswordAndCate(Users users)
	{
		return repository.findByUsernameAndCategory(users.getUsername(), users.getPassword(),users.getUsercategory().getId());
	}
	
	//this is for customers
	public Users getUserByEmailPasswordAndCate(Users users)
	{
		return repository.findByEmailAndCategory(users.getEmail(), users.getPassword(),users.getUsercategory().getId());
	}
	
	//checking if email exist
	public Users getUserByEmail(Users users)
	{
		return repository.findByEmail(users.getEmail());
	}
	
	public Users getUserById(Long userid) {
        Optional<Users> userOptional = repository.findById(userid);
        return userOptional.orElse(null);
    }
	
}
