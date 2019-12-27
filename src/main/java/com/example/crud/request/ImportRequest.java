package com.example.crud.request;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class ImportRequest {

    private Integer id;
    
    
    private Integer userId;
    	

    private Integer providerId;
    
 
    private String invoiceCode;
    

    private String description;
     	    

    private Integer paymentMethodId;
    
    private List<Integer> productIdList;

    
    
    
	public List<Integer> getProductIdList() {
		return productIdList;
	}


	public void setProductIdList(List<Integer> productIdList) {
		this.productIdList = productIdList;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getProviderId() {
		return providerId;
	}


	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}


	public String getInvoiceCode() {
		return invoiceCode;
	}


	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}


	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
    
    
}
