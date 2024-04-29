package com.goboushop.gobousmartshopping.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String fullName;
	@Column(nullable=false, unique = true)
	private String username;
	@Column(nullable=false)
	private String email;
	private String mobil_Number;
	@Column(nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private UsersCategory usercategory;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobil_Number() {
		return mobil_Number;
	}
	public void setMobil_Number(String mobil_Number) {
		this.mobil_Number = mobil_Number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsersCategory getUsercategory() {
		return usercategory;
	}
	public void setUsercategory(UsersCategory usercategory) {
		this.usercategory = usercategory;
	}
	
}
