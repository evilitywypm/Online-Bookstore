package com.software.demo.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	
	@ManyToOne
	private Role role;

	public Admin() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String email, String password, Role role) 
	{
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public Role getRole() 
	{
		return role;
	}

	public void setRole(Role role) 
	{
		this.role = role;
	}
	
	
	
	
	
}
