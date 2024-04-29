package com.goboushop.gobousmartshopping.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PurchaseProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="purchase_id")
	private Purchase purchase;
	
	private String quantity;
	private String totalCost;
	private String cost;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private ProductAdd product;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public ProductAdd getProduct() {
		return product;
	}
	public void setProduct(ProductAdd product) {
		this.product = product;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
}
