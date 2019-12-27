package com.example.crud.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private Integer enable;


    dsfd

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }


}
