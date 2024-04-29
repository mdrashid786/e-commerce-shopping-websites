package com.goboushop.gobousmartshopping.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class ProductTaxPercentage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int percentage;
	
	@ManyToOne
	@JoinColumn(name="tax_id")
	private ProductTax productTax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public ProductTax getProductTax() {
		return productTax;
	}

	public void setProductTax(ProductTax productTax) {
		this.productTax = productTax;
	}
	
	
}
