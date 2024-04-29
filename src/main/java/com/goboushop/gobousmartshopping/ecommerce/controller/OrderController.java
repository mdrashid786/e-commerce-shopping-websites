package com.goboushop.gobousmartshopping.ecommerce.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.beans.ProductAdd;
import com.goboushop.gobousmartshopping.beans.ProductImages;
import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.ecommerce.beans.CartItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.OrderItems;
import com.goboushop.gobousmartshopping.ecommerce.beans.Orders;
import com.goboushop.gobousmartshopping.ecommerce.beans.ShoppingCart;
import com.goboushop.gobousmartshopping.ecommerce.repository.OrdersRepository;
import com.goboushop.gobousmartshopping.ecommerce.service.CartItemService;
import com.goboushop.gobousmartshopping.ecommerce.service.OrderItemService;
import com.goboushop.gobousmartshopping.ecommerce.service.OrderService;
import com.goboushop.gobousmartshopping.repository.UsersRepository;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.ProductAddService;
import com.goboushop.gobousmartshopping.service.ProductSubCategoryService;
import com.goboushop.gobousmartshopping.service.ProductsImageService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrganisationService orgservice;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	CartItemService cartItemService;
	@Autowired
	ProductAddService prodservice;
	@Autowired
	ProductSubCategoryService subservice;
	
	@Autowired
	ProductsImageService prodImgService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	OrganisationService service;
	
	@Autowired
	UsersRepository usersRepository;
	

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//today's date
	LocalDate today = LocalDate.now();
	DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	String formattedDate = today.format(formater);
	
	@GetMapping("/showOrderPage")
	public String showFormOrganisation(Model model) {
		model.addAttribute("orders", new Orders());
		model.addAttribute("orderList", ordersRepository.findAllOrders());
		model.addAttribute("organisations", service.getAllOrganisation());
		return "/html/order/order";
	}
	
	@GetMapping("/viewOrder")
	public String viewproduct(Model model, @RequestParam("id") Long id) {
			    
		Orders orders = ordersRepository.findById(id).get();
		model.addAttribute(orders);
		model.addAttribute("organisations", orgservice.getAllOrganisation());
				 // Add Order Item
		List<OrderItems> orderItem = orders.getOrders(); 
		
		 for (OrderItems item : orderItem) {
	            
			 item.getQuantity();
	        }
		
		 model.addAttribute("orderItem", orderItem);		
				return "/html/order/order-view";
				
	}
	
	@GetMapping("/deliver")
	public String orderDelivery(Model model, @RequestParam("id") Long id) {
		String status = "delivered";
		orderService.UpdateOrderStatus(id, status);	   	
		return "redirect:showOrderPage";
	}
	
	@PostMapping("/placeOder")
	public String addOrder(@Valid @ModelAttribute Orders orders,RedirectAttributes redirectAttributes,BindingResult result,Model model,HttpSession session) throws Exception {
		// Getting user
		Long UserId =  (Long) session.getAttribute("userid");
		Users users = new Users();
		users.setId(UserId);
		
		
		//calculating order total
		Long userCartNo = (Long) session.getAttribute("usercart");
		int totalcart = 0;
		int itemCount = 0;
		if (userCartNo > 0) {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(userCartNo);
			List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
			itemCount = totalItems.size();
			 
			 // Iterate over totalItems and print each item
	        for (CartItems item : totalItems) {
	            
	            totalcart = totalcart + Integer.parseInt(item.getProduct().getCostPer()) * item.getQuantity()   ;
	        
	        }
	       
		}
		
		
		
		if(itemCount == 0 ) {	
			redirectAttributes.addFlashAttribute("message", "Your order is empty !!");
			return "redirect:/ecom/checkout";
		}else{
			if(result.hasErrors()) {
				model.addAttribute("productAdds",prodservice.getAllProducts());
				model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
				return "redirect:/ecom/checkout";
			}else {
				
				orders.setUser(users);
				orders.setTotal(totalcart);
				orders.setDate(formattedDate);
				orders.setStatus("received");
				//saving order
				orderService.placeOrder(orders);
				
				//loop for place Order Items
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setId(userCartNo);
				List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
				for (CartItems item : totalItems) {
			        
					ProductAdd product = new ProductAdd();
					product.setId(item.getProduct().getId());
					
					OrderItems orderItems = new OrderItems();
					orderItems.setOrders(orders);
					orderItems.setProduct(product);
					orderItems.setQuantity(item.getQuantity());
					orderItemService.addItem(orderItems);
			     }
				
				//loop for deleting User Shopping Cart Items
				for (CartItems item : totalItems) {
			        
					cartItemService.deleteItem(item.getId());
			     }
				
				
				redirectAttributes.addFlashAttribute("orderplacedmessage", "Your order is placed Successfully !!");
				
				return "redirect:/ecom/checkout";
			}
		}
	}
	
	//image displaying my image controller
		@GetMapping("/prodimage2/display/{id}")
		@ResponseBody
		void showImage2(@PathVariable("id") Long id, HttpServletResponse response, Optional<ProductImages> productImages)
				throws ServletException, IOException {
			log.info("Id :: " + id);
			productImages = prodImgService.GetProductImageByProductIdAndDocName(id, "todisplay");
			
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(productImages.get().getDoc());
			response.getOutputStream().close();
		}	
	
	
	
	//image displaying my image controller organisation
			@GetMapping("/image/display/{id}")
			@ResponseBody
			void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Organisation> organisation)
					throws ServletException, IOException {
				log.info("Id :: " + id);
				organisation = orgservice.getImageById(id);
				
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(organisation.get().getImage());
				response.getOutputStream().close();
			}
}
