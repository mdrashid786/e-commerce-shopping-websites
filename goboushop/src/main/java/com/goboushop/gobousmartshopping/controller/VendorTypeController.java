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

import com.goboushop.gobousmartshopping.beans.Bank;
import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.beans.VendorType;
import com.goboushop.gobousmartshopping.repository.BankRepository;
import com.goboushop.gobousmartshopping.repository.VendorTypeRepository;
import com.goboushop.gobousmartshopping.service.BankService;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.VendorTypeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/VendorType")
public class VendorTypeController {
	@Autowired
	VendorTypeService service;
	
	@Autowired
	VendorTypeRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorVendorType")
	public String showFormProductType(Model model) {
		VendorType vendorType = new VendorType();
		model.addAttribute("vendorType", vendorType);
		model.addAttribute("vendorTypes", service.getAllVendorTypes());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/finance/masterSetup-Finance-Vendor";
	}
	
	@PostMapping("/addVendorType")
	public String addProdCat(Model model,@Valid @ModelAttribute VendorType vendorType,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("vendorTypes", service.getAllVendorTypes());
		return "/html/masterSetup/finance/masterSetup-Finance-Vendor";
		}else {
			service.saveProductBrand(vendorType);
			return "redirect:showorVendorType";
		}
	}
	
	@GetMapping("/UpdateVendorType")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		VendorType vendorType = repository.findById(id).get();
		  model.addAttribute(vendorType);
		  model.addAttribute("vendorTypes", service.getAllVendorTypes());
		  return "/html/masterSetup/finance/masterSetup-Finance-Vendor";
	}
	@GetMapping("/deleteVendorType")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteVType(id);
			return "redirect:showorVendorType";
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
