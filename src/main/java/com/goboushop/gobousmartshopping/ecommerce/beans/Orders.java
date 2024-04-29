package com.goboushop.gobousmartshopping.ecommerce.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.goboushop.gobousmartshopping.beans.Users;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private Users user;
	 
	
	 private String address;
	
	 private String city;
	
	 private String state;
	
	 private String country;
	 
	 private String pincode;
	 private String contact;
	 private double total;
	 private String date;
	 
	 private String status;
	
	 
	 @OneToMany(mappedBy = "orders",targetEntity = OrderItems.class, cascade = CascadeType.ALL)
	 private List<OrderItems> orders;
	 
	 
	 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderItems> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderItems> orders) {
		this.orders = orders;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	 
	 
	 
	 
}
