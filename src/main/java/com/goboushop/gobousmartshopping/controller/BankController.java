package com.goboushop.gobousmartshopping.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
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

import com.goboushop.gobousmartshopping.beans.Bank;
import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.repository.BankRepository;
import com.goboushop.gobousmartshopping.service.BankService;
import com.goboushop.gobousmartshopping.service.OrganisationService;


@Controller
@RequestMapping("/Bank")
public class BankController {
	@Autowired
	BankService service;
	
	@Autowired
	BankRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@GetMapping("/showorBank")
	public String showFormProductType(Model model) {
		Bank bank = new Bank();
		model.addAttribute("bank", bank);
		model.addAttribute("banks", service.getAllBanks());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/finance/masterSetup-Finance-Bank";
	}
	
	@PostMapping("/addBank")
	public String addProdCat(Model model,@Valid @ModelAttribute Bank bank,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		model.addAttribute("banks", service.getAllBanks());
		return "/html/masterSetup/finance/masterSetup-Finance-Bank";
		}else {
			service.saveProductBrand(bank);
			return "redirect:showorBank";
		}
	}
	
	@GetMapping("/UpdateBank")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		Bank bank = repository.findById(id).get();
		  model.addAttribute(bank);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("banks", service.getAllBanks());
		  return "/html/masterSetup/finance/masterSetup-Finance-Bank";
	}
	@GetMapping("/deleteBank")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteBank(id);
			return "redirect:showorBank";
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
