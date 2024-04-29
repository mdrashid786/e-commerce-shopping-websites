package com.goboushop.gobousmartshopping.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.goboushop.gobousmartshopping.application.config.Ecryption;
import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.beans.Users;
import com.goboushop.gobousmartshopping.beans.UsersCategory;
import com.goboushop.gobousmartshopping.ecommerce.beans.Orders;
import com.goboushop.gobousmartshopping.ecommerce.service.OrderService;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.UsersService;
import com.goboushop.gobousmartshopping.service.userCategoryService;


@Controller
@RequestMapping("/admin")
public class loginController {
	@Autowired
	OrganisationService orgservice;
	
	@Autowired
	UsersService service;
	@Autowired
	userCategoryService usercatService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	OrderService orderService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/loginForm")
	public String showFormLogin(Model model) {
		model.addAttribute("users",new Users());
		return "/html/login/admin-login";
	}
		
	@GetMapping("/showAdminDashboard")
	public String showDashboard(Model model) {
		int totalOrderReceived = 0;
		int totalOrderDelivered = 0;
		int totalAdmin = 0;
		int totalCustomer = 0;
		List<Orders> orders = orderService.getAllOrders();
		
		for (Orders ord : orders) {
			System.out.println(ord.getStatus());
	          if(ord.getStatus().equalsIgnoreCase("received")) {
	        	  totalOrderReceived++;
	        	  System.out.println(totalOrderReceived);
	          }else {
	        	  totalOrderDelivered++;
	          }
			
	        }
		
		List<Users> users = (List<Users>) usersService.getAllUsers();
		for (Users u : users) {
			
			Long user = (long) u.getUsercategory().getId();
	          if(user == 2) {
	        	  totalCustomer++;
	        	
	          }else {
	        	  totalAdmin++;
	          }
			
	        }
		model.addAttribute("totalCustomer", totalCustomer);
		model.addAttribute("totalAdmin", totalAdmin);
		model.addAttribute("totalOrderReceived", totalOrderReceived);
		model.addAttribute("totalOrderDelivered", totalOrderDelivered);
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "home";
	}
	
	@PostMapping("/checkuser")
	public String checkuser(Model model, @ModelAttribute Users users,RedirectAttributes redirectAttributes, BindingResult result,HttpServletRequest request,@RequestParam("username") String username) {
		
		UsersCategory usercat =  usercatService.getUserById((long) 1);
		users.setUsercategory(usercat);
		
		//encrypting my password
		 String originalData = users.getPassword();
	     byte[] originalBytes = originalData.getBytes();

	     // Encoding
	     String encodedData = Ecryption.encode(originalBytes);
      
	     users.setPassword(encodedData);
		
		Users user= service.getUserByUsernamePasswordAndCate(users);
		if(user==null)
		{
			System.out.println("invalid");
			redirectAttributes.addFlashAttribute("message", "Login or password incorect !");
			return "redirect:loginForm";
		}
		else
		{
			HttpSession session = request.getSession();
	        session.setAttribute("username", username);
			return "redirect:showAdminDashboard";
		}
	}
	
	 @GetMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session = request.getSession();
	        session.invalidate();
	        // Set cache-control header to prevent caching of the page
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	        response.setHeader("Expires", "0"); // Proxies
	        
	        return "redirect:loginForm";
	    }
	
	//image displaying my image controller
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
