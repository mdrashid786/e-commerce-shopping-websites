package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.beans.UsersCategory;
@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
	//this is for admin 
	@Query("SELECT u FROM Users u WHERE u.username=?1 and u.password=?2 and u.usercategory.id = ?3")
    Users findByUsernameAndCategory(String username,String password, Long usersCategory_id);
	
	//this is for customer authentication
	@Query("SELECT u FROM Users u WHERE u.email=?1 and u.password=?2 and u.usercategory.id = ?3")
    Users findByEmailAndCategory(String email,String password, Long usersCategory_id);
	
	@Query("SELECT u FROM Users u WHERE u.username=?1 and u.password=?2")
    Users findByUsername(String username,String password);
	
	@Query("SELECT u FROM Users u WHERE u.email=?1")
    Users findByEmail(String email);
	
	@Query("SELECT u FROM Users u WHERE u.password=?1")
    Users findByPassword(String password);
}
