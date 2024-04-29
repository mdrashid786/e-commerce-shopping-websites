package com.goboushop.gobousmartshopping.beans;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ProductTax {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "tax is required")
	private String taxName;
	
	@OneToMany(mappedBy = "productTax",targetEntity = ProductTaxPercentage.class, cascade = CascadeType.ALL)
	private List<ProductTaxPercentage> tax_list;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	
}
