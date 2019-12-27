package com.example.crud.model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "user_id")
    private Integer userId;
    

    @Column(name = "tax_code")
    private Integer taxCode;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updatedDate;
    
    

	@Column(name = "payment_method_id")
    private Integer paymentMethodId;
    

    @Column(name = "total_price")
    private long totalPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

       
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }





    public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	 
	
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

 
}
