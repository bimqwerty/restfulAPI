package com.example.crud.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "import")
public class Import implements Serializable{
	 private static final long serialVersionUID = 1L;

	    @Id
	    @Column(name="id")
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	    
	    
	    @Column(name = "user_id")
	    private Integer userId;
	    	
		@Column(name = "provider_id")
	    private Integer providerId;
	    
	    @Column(name="invoice_code")
	    private String invoiceCode;
	    
	    @Column(name="description")
	    private String description;
	    
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

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	    					    
}
