package com.goboushop.gobousmartshopping.beans;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ProductSubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Category is required")
	private String categoryName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private ProductCategories category;

	public ProductCategories getCategory() {
		return category;
	}

	public void setCategory(ProductCategories category) {
		this.category = category;
	}

	public List<ProductAdd> getProd_list() {
		return prod_list;
	}

	public void setProd_list(List<ProductAdd> prod_list) {
		this.prod_list = prod_list;
	}

	@OneToMany(mappedBy = "subcat1",targetEntity = ProductAdd.class, cascade = CascadeType.ALL)
	private List<ProductAdd> prod_list;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
