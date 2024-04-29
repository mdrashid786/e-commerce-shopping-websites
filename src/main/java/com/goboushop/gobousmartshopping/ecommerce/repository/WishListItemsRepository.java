package com.goboushop.gobousmartshopping.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goboushop.gobousmartshopping.ecommerce.beans.WishList;
import com.goboushop.gobousmartshopping.ecommerce.beans.WishListItems;


@Repository
public interface WishListItemsRepository extends JpaRepository<WishListItems, Long> {
	@Query("SELECT u FROM WishListItems u WHERE u.wishList=?1")
	List<WishListItems> findByWishListId(WishList wishListId);
}
