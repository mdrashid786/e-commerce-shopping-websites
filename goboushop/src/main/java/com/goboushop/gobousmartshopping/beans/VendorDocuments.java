package com.goboushop.gobousmartshopping.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class VendorDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String docName;
	private String docType;
	
	@Lob
    private byte[] doc;
	

	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendordoc;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getDocType() {
		return docType;
	}


	public void setDocType(String docType) {
		this.docType = docType;
	}


	public byte[] getDoc() {
		return doc;
	}


	public void setDoc(byte[] doc) {
		this.doc = doc;
	}


	public Vendor getVendordoc() {
		return vendordoc;
	}


	public void setVendordoc(Vendor vendordoc) {
		this.vendordoc = vendordoc;
	}
	
	
	
}
