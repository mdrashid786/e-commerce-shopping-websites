
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
import com.goboushop.gobousmartshopping.ecommerce.beans.CartItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.ShoppingCart;
import com.goboushop.gobousmartshopping.ecommerce.repository.CartItemRepository;
import com.goboushop.gobousmartshopping.ecommerce.service.CartItemService;
import com.goboushop.gobousmartshopping.service.ProductAddService;
import com.goboushop.gobousmartshopping.service.ProductsImageService;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	CartItemService cartItemService;

	@Autowired
	ProductAddService productService;
	
	@Autowired
	ProductsImageService prodImgService;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Integer> addItemToCart(@RequestParam("productId") Long productId,
			@RequestParam("quantity") int quantity, HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		
		
		// Setting product Id
		ProductAdd product = new ProductAdd();
		product.setId(productId);

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(userCartNo);

		CartItems cartItems = new CartItems();
		cartItems.setShoppingCart(shoppingCart);
		cartItems.setProduct(product);
		cartItems.setQuantity(quantity);
		
		 boolean exists = cartItemService.isProductAdded(shoppingCart, product);
		 if (exists) {
		    System.out.println("item exist");
		    CartItems cartIte = cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
		    cartIte.setQuantity(cartIte.getQuantity() + 1);
		    cartItemRepository.save(cartIte);
		 } else {
			System.out.println("item does exist");
			cartItemService.addItem(cartItems);
		 }
		
		List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
		int itemCount = totalItems.size();
		

		return ResponseEntity.ok(itemCount);
	}

	@PostMapping("/increaseQtyItemToCart")
	@ResponseBody
	public ResponseEntity<Integer> increaseQtyItemToCart(@RequestParam("productId") Long productId,
			 HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		
		
		// Setting product Id
		ProductAdd product = new ProductAdd();
		product.setId(productId);

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(userCartNo);

		 //boolean exists = cartItemService.isProductAdded(shoppingCart, product);
		
		 CartItems cartIte = cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
		 cartIte.setQuantity(cartIte.getQuantity() + 1);
		 cartItemRepository.save(cartIte);
		
		
		List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
		int itemCount = totalItems.size();
		

		return ResponseEntity.ok(itemCount);
	}
	
	@PostMapping("/dicreaseQtyItemToCart")
	@ResponseBody
	public ResponseEntity<Integer> dicreaseQtyItemToCart(@RequestParam("productId") Long productId,
			 HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		
		
		// Setting product Id
		ProductAdd product = new ProductAdd();
		product.setId(productId);

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(userCartNo);

		 //boolean exists = cartItemService.isProductAdded(shoppingCart, product);
		
		 CartItems cartIte = cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
		 
		 if(cartIte.getQuantity() > 1) {
			 cartIte.setQuantity(cartIte.getQuantity() - 1);
			 cartItemRepository.save(cartIte);
		 }
		
		
		
		List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
		int itemCount = totalItems.size();
		

		return ResponseEntity.ok(itemCount);
	}
	
	
	
	
	

	@GetMapping("/updateCartSize")
	@ResponseBody
	public ResponseEntity<Integer> updateCartSize(HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		int itemCount = 0;
		if (userCartNo > 0) {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(userCartNo);
			
			List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
			
			itemCount = totalItems.size();
			System.out.println("onreload cartsize: " + itemCount);
			
			 // Iterate over totalItems and print each item
	        for (CartItems item : totalItems) {
	            System.out.println("id :"+item.getQuantity()+" cartNo : "+item.getShoppingCart().getId()+" prodId :"+item.getProduct().getProductName()+" qty :"+item.getQuantity()); // Adjust this line based on the CartItems class's toString() method
	        }
			
		}
		return ResponseEntity.ok(itemCount);
	}

	
	@GetMapping("/updateCartTotal")
	@ResponseBody
	public ResponseEntity<Integer> updateCartTotal(HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		int totalcart = 0;
		if (userCartNo > 0) {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(userCartNo);
			List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
			
			 // Iterate over totalItems and print each item
	        for (CartItems item : totalItems) {
	            System.out.println("id :"+item.getQuantity()+" cartNo : "+item.getShoppingCart().getId()+" prodId :"+item.getProduct().getProductName()+" qty :"+item.getQuantity()); // Adjust this line based on the CartItems class's toString() method
	            totalcart = totalcart + Integer.parseInt(item.getProduct().getCostPer()) * item.getQuantity()   ;
	        
	        }
			
		}
		return ResponseEntity.ok(totalcart);
	}
	
	@GetMapping("/cartItems")
	@ResponseBody
	public ResponseEntity<List<CartItems>> getcartItems(HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(userCartNo);
			List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
			
			 // Iterate over totalItems and print each item
	        for (CartItems item : totalItems) {
	            System.out.println("id :"+item.getId()+" cartNo : "+item.getShoppingCart().getId()+" prodId :"+item.getProduct().getId()+" qty :"+item.getQuantity()); // Adjust this line based on the CartItems class's toString() method
	        }
			
		return ResponseEntity.ok(totalItems);
	}

	

	@GetMapping("/remove")
	public String removeItemFromCart(@RequestParam("id") Long ItemId) {
		cartItemService.deleteItem(ItemId);
		return "redirect:/ecom/viewCart";
	}
	
	//image displaying my image controller
	@GetMapping("/prodimage2/display/{id}")
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
