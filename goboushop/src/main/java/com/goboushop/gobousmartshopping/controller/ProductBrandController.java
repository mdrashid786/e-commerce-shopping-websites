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
import com.goboushop.gobousmartshopping.beans.ProductType;
import com.goboushop.gobousmartshopping.repository.ProductBrandRepository;
import com.goboushop.gobousmartshopping.repository.ProductTypeRepository;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.ProductBrandService;
import com.goboushop.gobousmartshopping.service.ProductTypeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ProductBrand")
public class ProductBrandController {
	@Autowired
	ProductBrandService service;
	
	@Autowired
	ProductBrandRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdBrand")
	public String showFormProductType(Model model) {
		ProductBrand productBrand = new ProductBrand();
		model.addAttribute("productBrand", productBrand);
		model.addAttribute("productBrands", service.getAllProductBrand());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Brand";
	}
	
	@PostMapping("/addBrand")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductBrand productBrand,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("productBrands", service.getAllProductBrand());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Brand";
		}else {
			service.saveProductBrand(productBrand);
			return "redirect:showorProdBrand";
		}
	}
	
	@GetMapping("/UpdateProdBrand")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductBrand productBrand = repository.findById(id).get();
		  model.addAttribute(productBrand);
		  model.addAttribute("productBrands", service.getAllProductBrand());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Brand";
	}
	@GetMapping("/deleteProdBrand")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdBrand(id);
			return "redirect:showorProdBrand";
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
