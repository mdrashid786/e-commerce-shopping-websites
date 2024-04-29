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
public class PayementMode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToMany(mappedBy = "payementMode",targetEntity = Purchase.class, cascade = CascadeType.ALL)
	private List<Purchase> payementMode_list;
	
	
	@NotBlank(message = "mode is required")
	private String pmode;
	
	public String getPmode() {
		return pmode;
	}
	public void setPmode(String pmode) {
		this.pmode = pmode;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
