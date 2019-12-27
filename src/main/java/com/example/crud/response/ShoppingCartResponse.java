package com.example.crud.response;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ShoppingCartResponse {

    
    private Integer id;

    
    private Integer billId;
    

    
    private String productName;
    

   
    private Integer amount;
    

   
    private Double totalPrice;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getBillId() {
		return billId;
	}



	public void setBillId(Integer billId) {
		this.billId = billId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public Integer getAmount() {
		return amount;
	}



	public void setAmount(Integer amount) {
		this.amount = amount;
	}



	public Double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
    
    
}
