package com.goboushop.gobousmartshopping.controller;

import java.io.IOException;
import java.util.Optional;

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

import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.beans.ProductBrand;
import com.goboushop.gobousmartshopping.beans.ProductColor;
import com.goboushop.gobousmartshopping.repository.ProductBrandRepository;
import com.goboushop.gobousmartshopping.repository.ProductColorRepository;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.ProductBrandService;
import com.goboushop.gobousmartshopping.service.ProductColorService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ProductColor")
public class ProductColorController {
	@Autowired
	ProductColorService service;
	
	@Autowired
	ProductColorRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdColor")
	public String showFormProductType(Model model) {
		ProductColor productColor = new ProductColor();
		model.addAttribute("productColor", productColor);
		model.addAttribute("productColors", service.getAllProductColors());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Color";
	}
	
	@PostMapping("/addColor")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductColor productColor,BindingResult result) throws Exception {
	if(result.hasErrors()) {
			
		model.addAttribute("productColors", service.getAllProductColors());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Color";
		}else {
			service.saveProductBrand(productColor);
			return "redirect:showorProdColor";
		}
	}
	
	@GetMapping("/UpdateProdColor")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductColor productColor = repository.findById(id).get();
		  model.addAttribute(productColor);
		  model.addAttribute("productColors", service.getAllProductColors());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Color";
	}
	@GetMapping("/deleteProdColor")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdColor(id);
			return "redirect:showorProdColor";
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
