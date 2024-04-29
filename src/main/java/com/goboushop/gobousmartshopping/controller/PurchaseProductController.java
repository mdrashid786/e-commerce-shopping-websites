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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.beans.Purchase;
import com.goboushop.gobousmartshopping.beans.PurchaseProduct;
import com.goboushop.gobousmartshopping.repository.PurchaseAddRepository;
import com.goboushop.gobousmartshopping.repository.PurchaseProductRepository;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.ProductAddService;
import com.goboushop.gobousmartshopping.service.PurchaseAddService;
import com.goboushop.gobousmartshopping.service.PurchaseProductService;

@Controller
@RequestMapping("/PurchaseProduct")
public class PurchaseProductController {
	@Autowired
	OrganisationService orgservice;
	
	@Autowired
	PurchaseAddService purchaseAddService;
	
	@Autowired
	PurchaseProductRepository repository;
	
	@Autowired
	PurchaseAddRepository purchaserepository;
	
	@Autowired
	PurchaseProductService service;
	
	@Autowired
	ProductAddService prodservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	Purchase purchase_id = new Purchase();
	
	@GetMapping("/showorPurchaseProduct")
	public String showFormPurchase(Model model, @RequestParam("id") Long id) {
		PurchaseProduct purchaseProduct = new PurchaseProduct();
		purchase_id.setId(id);
		model.addAttribute(purchaseProduct);	
		model.addAttribute("productAdds",prodservice.getAllProducts());
		model.addAttribute("purchaseProducts", service.getAllPurchaseProduct());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/purchase/purchase-Products";
	}
	
	@PostMapping("/addPurchaseProduct")
	public String addProdCat(Model model,@Valid @ModelAttribute PurchaseProduct purchaseproduct,BindingResult result,RedirectAttributes redirectAttributes) throws Exception {
		if(result.hasErrors()) {	
				
				model.addAttribute("productAdds",prodservice.getAllProducts());
				model.addAttribute("purchaseProducts", service.getAllPurchaseProduct());
				return "/html/purchase/purchase-Products";
			}else {
				purchaseproduct.setPurchase(purchase_id);
			    service.savePurchase(purchaseproduct);
			    redirectAttributes.addFlashAttribute("message", "Stock added successfully!!!");
			return "redirect:showorPurchaseProduct?id="+purchase_id.getId();
			}
	}
	
	@GetMapping("/showStocklist")
	public String showproductManage(Model model) {
		model.addAttribute("purchaseProducts", service.getAllPurchaseProduct());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "html/stock/stock";
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