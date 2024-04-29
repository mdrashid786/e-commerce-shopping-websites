package com.goboushop.gobousmartshopping.beans;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ProductCategories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Category is required")
	private String categoryName;

	@OneToMany(mappedBy = "prodcategory",targetEntity = ProductAdd.class, cascade = CascadeType.ALL)
	private List<ProductAdd> prod_list;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, targetEntity = ProductSubCategory.class, cascade = CascadeType.ALL)
	private List<ProductSubCategory> prodcat_list;
	
//	//Many to Many relation with vendor
//	@ManyToMany(mappedBy = "productCategories")
//    private Set<Vendor> vendors = new HashSet<>();
	
	
	
	public List<ProductSubCategory> getProdcat_list() {
		return prodcat_list;
	}

	public void setProdcat_list(List<ProductSubCategory> prodcat_list) {
		this.prodcat_list = prodcat_list;
	}

	public List<ProductAdd> getProd_list() {
		return prod_list;
	}

	public void setProd_list(List<ProductAdd> prod_list) {
		this.prod_list = prod_list;
	}

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
