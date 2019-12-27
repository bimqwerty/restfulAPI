package com.example.crud.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="price")
    private double price;
    
    @Column(name="description")
    private String description;

    public Product() {
    }
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


    
}