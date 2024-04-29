package com.goboushop.gobousmartshopping.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goboushop.gobousmartshopping.beans.ProductAdd;
import com.goboushop.gobousmartshopping.beans.ProductImages;
import com.goboushop.gobousmartshopping.ecommerce.beans.WishList;
import com.goboushop.gobousmartshopping.ecommerce.beans.WishListItems;
import com.goboushop.gobousmartshopping.ecommerce.service.WishListItemService;
import com.goboushop.gobousmartshopping.service.ProductAddService;
import com.goboushop.gobousmartshopping.service.ProductsImageService;

@Controller
@RequestMapping("/wishList")
public class WishListController {
	@Autowired
	WishListItemService wishListItemService;

	@Autowired
	ProductAddService productService;
	
	@Autowired
	ProductsImageService prodImgService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Integer> addItemToCart(@RequestParam("productId") Long productId, HttpSession session) {
		// Getting session
		Long userWishListNo = (Long) session.getAttribute("userWishList");

		// Setting product Id
		ProductAdd product = new ProductAdd();
		product.setId(productId);

		WishList wishList = new WishList();
		wishList.setId(userWishListNo);
		
		WishListItems wishListItems = new WishListItems(); 
		wishListItems.setWishList(wishList);
		wishListItems.setProduct(product);

		wishListItemService.addItem(wishListItems);

		List<WishListItems> totalItems = wishListItemService.getTotalItems(wishList);
		int itemCount = totalItems.size();
		System.out.println("itemCount: " + itemCount);
		
		 // Iterate over totalItems and print each item
        for (WishListItems item : totalItems) {
            System.out.println("id :"+item.getId()+" WishListNo : "+item.getWishList().getId()+" prodId :"+item.getProduct().getId()); // Adjust this line based on the CartItems class's toString() method
        }

		return ResponseEntity.ok(itemCount);
	}
	
	
	@GetMapping("/updateWishListSize")
	@ResponseBody
	public ResponseEntity<Integer> updateCartSize(HttpSession session) {
		// Getting session
		Long userWishListNo = (Long) session.getAttribute("userWishList");
		int itemCount = 0;
		
		if (userWishListNo > 0) {
			WishList wishList = new WishList();
			wishList.setId(userWishListNo);
			
			
			List<WishListItems> totalItems = wishListItemService.getTotalItems(wishList);
			itemCount = totalItems.size();
			
		}
		return ResponseEntity.ok(itemCount);
	}
	
	@GetMapping("/remove")
	public String removeItemFromCart(@RequestParam("id") Long ItemId) {
		wishListItemService.deleteItem(ItemId);
		return "redirect:/ecom/viewWishList";
	}
	
	
	//image displaying my image controller
	@GetMapping("/prodimage3/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ProductImages> productImages)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		productImages = prodImgService.GetProductImageByProductIdAndDocName(id, "todisplay");
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(productImages.get().getDoc());
		response.getOutputStream().close();
	}	
	
}
