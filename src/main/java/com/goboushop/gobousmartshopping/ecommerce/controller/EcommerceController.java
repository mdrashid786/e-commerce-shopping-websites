package com.goboushop.gobousmartshopping.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goboushop.gobousmartshopping.beans.ProductAdd;
import com.goboushop.gobousmartshopping.beans.ProductImages;
import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.ecommerce.beans.CartItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.OrderItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.Orders;
import com.goboushop.gobousmartshopping.ecommerce.beans.ShoppingCart;
import com.goboushop.gobousmartshopping.ecommerce.beans.WishList;
import com.goboushop.gobousmartshopping.ecommerce.beans.WishListItems;
import com.goboushop.gobousmartshopping.ecommerce.repository.OrdersRepository;
import com.goboushop.gobousmartshopping.ecommerce.service.CartItemService;
import com.goboushop.gobousmartshopping.ecommerce.service.OrderService;
import com.goboushop.gobousmartshopping.ecommerce.service.ShoppingCartService;
import com.goboushop.gobousmartshopping.ecommerce.service.WishListItemService;
import com.goboushop.gobousmartshopping.repository.ProductAddRepository;
import com.goboushop.gobousmartshopping.service.ProductAddService;
import com.goboushop.gobousmartshopping.service.ProductSubCategoryService;
import com.goboushop.gobousmartshopping.service.ProductsImageService;
import com.goboushop.gobousmartshopping.service.UsersService;

@Controller
@RequestMapping("/ecom")
public class EcommerceController {
	
	ShoppingCartService cartService = new ShoppingCartService();
	
	@Autowired
	ProductAddService prodservice;
	@Autowired
	ProductsImageService prodImgService;
	
	@Autowired
	ProductSubCategoryService subservice;
	
	@Autowired
	ProductAddRepository repository;
	
	@Autowired
	WishListItemService wishListItemService;
	
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	OrderService orderService;
	
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showhomepage")
	public String showFormOrganisation(Model model) {
		model.addAttribute("productAdds",prodservice.getAllProducts());
		model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		return "/html/ecommerce/index";
	}
	
	 public static String status = "profile";
	
	@GetMapping("/profile")
	public String ShowProfile(Model model, HttpSession session) {
		Long userid = (Long) session.getAttribute("userid");
		
		//this object is for changing password
		Users user = new Users();
		user.setId(userid);
		
		
		//this object is for changing user profile
		Users userprofile = usersService.getUserById(userid);
		
		
		model.addAttribute("orderList", ordersRepository.findAllByUseryu(user));
		model.addAttribute("user", user);
		model.addAttribute("userprofile", userprofile);
		model.addAttribute("status", status);
		
		return "/html/ecommerce/profile";
	}
	@GetMapping("/profileOrderView")
	public String profileOrderView(Model model, @RequestParam("id") Long id, HttpSession session ) {
	    
		Orders orders = ordersRepository.findById(id).get();
		model.addAttribute(orders);
		
				 // Add Order Item
		List<OrderItems> orderItem = orders.getOrders(); 
		
		 for (OrderItems item : orderItem) {
	            
			 item.getQuantity();
	        }
		
		 model.addAttribute("orderItem", orderItem);	
		 
		 
			Long userid = (Long) session.getAttribute("userid");
			Users user = new Users();
			user.setId(userid);
			model.addAttribute("orderList", orderService.getByUser(user));
		 
		return "/html/ecommerce/profileOrderView";
	}
	
	
	@GetMapping("/checkout")
	public String showcheckPage(Model model, HttpSession session) {
		//creating Oder object
		model.addAttribute("orders",new Orders());
		
		
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(userCartNo);
		List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
	
	 // Iterate over totalItems and print each item
    	for (CartItems item : totalItems) {
        System.out.println("id :"+item.getId()+" cartNo : "+item.getShoppingCart().getId()+" prodId :"+item.getProduct().getCostPer()+" qty :"+item.getQuantity()); // Adjust this line based on the CartItems class's toString() method
    	}
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("productAdds",prodservice.getAllProducts());
		model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		return "/html/ecommerce/checkout";
	}
	
	
	@GetMapping("/viewCart")
	public String showViewCartPage(Model model, HttpSession session) {
		// Getting session
		Long userCartNo = (Long) session.getAttribute("usercart");
		
	ShoppingCart shoppingCart = new ShoppingCart();
	shoppingCart.setId(userCartNo);
	List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
	
	 // Iterate over totalItems and print each item
    for (CartItems item : totalItems) {
        System.out.println("id :"+item.getId()+" cartNo : "+item.getShoppingCart().getId()+" prodId :"+item.getProduct().getCostPer()+" qty :"+item.getQuantity()); // Adjust this line based on the CartItems class's toString() method
    }
		model.addAttribute("totalItems", totalItems);
		
		model.addAttribute("productAdds",prodservice.getAllProducts());
		model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		return "/html/ecommerce/view-cart";
	}
	
	
	
	
	@GetMapping("/viewWishList")
	public String showViewWishListPage(Model model, HttpSession session) {
		// Getting session
		Long userWishListNo = (Long) session.getAttribute("userWishList");
		
	WishList wishList = new WishList();
	wishList.setId(userWishListNo);
	
	List<WishListItems> totalItems = wishListItemService.getTotalItems(wishList);
	
	model.addAttribute("totalItems", totalItems);
		
	model.addAttribute("productAdds",prodservice.getAllProducts());
	model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		return "/html/ecommerce/view-wishlist";
	}
	
	@GetMapping("/viewProduct")
	public String viewproduct(Model model, @RequestParam("id") Long id) {
			    ProductAdd productAdd = repository.findById(id).get();
				model.addAttribute("productAdd", productAdd);
				 // Add the doc_list associated with the ProductAdd object to the model
				List<ProductImages> docList = productAdd.getDoc_list();
				
				
				
				 // Convert the bytes to Base64-encoded strings for display in the view
			    List<String> imageList = new ArrayList<>();
			    for (ProductImages image : docList) {
			    	 if (image.getDoc() != null) {
			    		 byte[] imageData = image.getDoc();
			    		 String base64Image = Base64.getEncoder().encodeToString(imageData);
			    		
			    		 imageList.add(base64Image);
			    	 }
			    }
				
			    model.addAttribute("imageList", imageList);
		
			    
				model.addAttribute("productAdds", prodservice.getAllProducts());
			
				return "html/ecommerce/products-view";
	}
	
	//image displaying my image controller
			@GetMapping("/prodimage/display/{id}")
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
