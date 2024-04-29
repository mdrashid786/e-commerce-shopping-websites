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
import com.goboushop.gobousmartshopping.beans.PayementMode;
import com.goboushop.gobousmartshopping.repository.PayementModeRepository;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.PayementModeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/PayementMode")
public class PayementModeController {
	
	@Autowired
	PayementModeService service;
	
	@Autowired
	PayementModeRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorPayementMode")
	public String showFormProductType(Model model) {
		PayementMode payementMode = new PayementMode();
		model.addAttribute("payementMode", payementMode);
		model.addAttribute("payementModes", service.getAllProductPMode());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/finance/masterSetup-Finance-PayementMode";
	}
	
	@PostMapping("/addPayementMode")
	public String addProdCat(Model model, @Valid @ModelAttribute PayementMode payementMode,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("payementModes", service.getAllProductPMode());
		return "/html/masterSetup/finance/masterSetup-Finance-PayementMode";
		}else {
			service.saveProductPayemode(payementMode);
			return "redirect:showorPayementMode";
		}
	}
	
	@GetMapping("/UpdatePayementMode")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		PayementMode payementMode = repository.findById(id).get();
		  model.addAttribute(payementMode);
		  model.addAttribute("payementModes", service.getAllProductPMode());
		  return "/html/masterSetup/finance/masterSetup-Finance-PayementMode";
	}
	@GetMapping("/deletePayementMode")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdPmode(id);
			return "redirect:showorPayementMode";
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
