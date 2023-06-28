package com.software.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Orders 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Date orderdate;
	private double totalprice;
	private String status;
	
	@ManyToOne
	private Customer customer;

	public Orders() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Integer id, Date orderdate, double totalprice, String status, Customer customer) 
	{
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.totalprice = totalprice;
		this.status = status;
		this.customer = customer;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	public Date getOrderdate() 
	{
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public double getTotalprice() 
	{
		return totalprice;
	}

	public void setTotalprice(double totalprice) 
	{
		this.totalprice = totalprice;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}
	
	
	
	
	
}
