package com.goboushop.gobousmartshopping.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.beans.ProductCategories;
import com.goboushop.gobousmartshopping.beans.Vendor;
import com.goboushop.gobousmartshopping.beans.VendorDocuments;
import com.goboushop.gobousmartshopping.repository.ProductImageRepository;
import com.goboushop.gobousmartshopping.repository.VendorDocumentRepository;
import com.goboushop.gobousmartshopping.repository.VendorRepository;
import com.goboushop.gobousmartshopping.service.OrganisationService;
import com.goboushop.gobousmartshopping.service.ProductCategoryService;
import com.goboushop.gobousmartshopping.service.ProductSubCategoryService;
import com.goboushop.gobousmartshopping.service.VendorService;
import com.goboushop.gobousmartshopping.service.VendorTypeService;

@Controller
@RequestMapping("/Vendor")
public class VendorController {
	@Autowired
	VendorService service;
	@Autowired
	VendorRepository repository;
	
	@Autowired
	VendorTypeService vendorTypeservice;
	
	@Autowired
	VendorTypeService vtypeservice;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	ProductSubCategoryService subservice;
	@Autowired
	VendorDocumentRepository vendorDocpository;
	
	@Autowired
	ProductCategoryService prodcatservice;
	
	@Autowired
	ProductImageRepository prodImagerepository;
	
	@GetMapping("/showvendor")
	public String showFormProduct(Model model) {
		Vendor vendor = new Vendor();
		model.addAttribute("vendor", vendor);
		model.addAttribute("vendors",service.getAllVendors());
		model.addAttribute("vendorTypes", vendorTypeservice.getAllVendorTypes());
		model.addAttribute("prodcategories", prodcatservice.getAllProductCategories());
		
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "html/vendor/vendor";
	}
	
	
	@GetMapping("/showVendorManage")
	public String showproductManage(Model model) {
		model.addAttribute("vendors",service.getAllVendors());
		
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "html/vendor/vendor-Manage";
		//return "html/products/data";
	}
	
	@GetMapping("/viewVendor")
	public String viewproduct(Model model, @RequestParam("id") Long id) {
			    Vendor vendor = repository.findById(id).get();
			    
			    
			    for (ProductCategories category : vendor.getProductCategories()) {
			        // Perform operations with each category
			        System.out.println("sinepolo"+category.getCategoryName());
			    }
			    
			    
			    model.addAttribute(vendor);
				
				
				
				model.addAttribute("vendors",service.getAllVendors());
				
				model.addAttribute("organisations", orgservice.getAllOrganisation());
				return "html/vendor/vendor-view";
	}
	
	@GetMapping("/UpdateVendor")
	public String UpdateOrganisation(Model model, @RequestParam("id") Long id) {
		  		Vendor vendor = repository.findById(id).get();
		  		model.addAttribute(vendor);
				model.addAttribute("vendors",service.getAllVendors());
				model.addAttribute("prodcategories", prodcatservice.getAllProductCategories());
				model.addAttribute("vendorTypes", vtypeservice.getAllVendorTypes());
				model.addAttribute("organisations", orgservice.getAllOrganisation());
				return "html/vendor/vendor";
	}
	
	@GetMapping("/deleteVendor")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteVendor(id);
			return "redirect:showVendorManage";
	}
	@PostMapping("/addVendor")
	public String addProdCat(Model model,@Valid @ModelAttribute Vendor vendor,BindingResult result,@RequestParam("image") MultipartFile[] files,@RequestParam("doucumentName") String[] docName,@RequestParam("productCategories") List<Long> selectedCategories) throws Exception {
			if(result.hasErrors()) {	
				model.addAttribute("vendors",service.getAllVendors());
				return "html/vendor/vendor";
			}else {
				 // Save the Productdoc object to the persistence
				 LocalDateTime currentDateTime = LocalDateTime.now();
			     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			     DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			     String formattedDate = currentDateTime.format(formatter);
			     String formatedTime = currentDateTime.format(timeFormatter);
			     System.out.println("Current date: " + formattedDate);
			     System.out.println("Current date: " + formatedTime);
				
			    service.saveProduct(vendor);
			    
			    service.addvendorinproductCategories(vendor, selectedCategories);
				
			    for (Long categoryId : selectedCategories) {
		            System.out.println("Selected Category ID: " + categoryId);
		        }
			    
				 // Save the uploaded images to the persistence
				
				int i = 0;
				for (MultipartFile file : files) {
					 	
					if(!file.isEmpty()) {
			        	VendorDocuments vendorDocuments = new VendorDocuments();
				        
			        	vendorDocuments.setDocName(docName[i]);
			        	
			        	vendorDocuments.setDocType(file.getContentType());
			        	vendorDocuments.setDoc(file.getBytes());
				        i++;
				        vendorDocuments.setVendordoc(vendor);
				        vendorDocpository.save(vendorDocuments);
			        }
			    	
			    }
				
			return "redirect:showvendor?message=Vendor added successfully !!!!";
			}
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
