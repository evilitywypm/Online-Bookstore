package com.software.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String street;
	private String township;
	private String state;
	private String country;
	private int postcode;
	
	@ManyToOne
	private Customer customer;

	public CustomerInfo() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerInfo(Integer id, String street, String township, String state, String country, int postcode,
			Customer customer) 
	{
		super();
		this.id = id;
		this.street = street;
		this.township = township;
		this.state = state;
		this.country = country;
		this.postcode = postcode;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

	
	
	
	
}
