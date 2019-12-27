package com.example.crud.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_method", schema = "productmanager", catalog = "")
public class PaymentMethod {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	

    @Column(name = "name")
    private String name;
    

    @Column(name = "description")
    private String description;

 
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
