package com.goboushop.gobousmartshopping.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.goboushop.gobousmartshopping.beans.Organisation;
import com.goboushop.gobousmartshopping.service.OrganisationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Dashboard")
public class dashboardController {
	@Autowired
	OrganisationService service;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showDashboard")
	public String showFormDashboard(Model model) {
		model.addAttribute("organisations", service.getAllOrganisation());
		return "home";
	}
	
	
	
	//image displaying my image controller
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Organisation> organisation)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		organisation = service.getImageById(id);
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(organisation.get().getImage());
		response.getOutputStream().close();
	}
	
}
