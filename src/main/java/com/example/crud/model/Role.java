package com.example.crud.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    

    @Column(name = "rolename")
    private String rolename;
    

    @Column(name = "description")
    private String description;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

 
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
