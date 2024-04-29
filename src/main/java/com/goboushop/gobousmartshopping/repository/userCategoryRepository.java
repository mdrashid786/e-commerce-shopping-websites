package com.goboushop.gobousmartshopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.beans.UsersCategory;
@Repository
public interface userCategoryRepository extends CrudRepository<UsersCategory, Long> {

}
