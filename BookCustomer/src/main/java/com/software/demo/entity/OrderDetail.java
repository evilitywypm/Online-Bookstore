package com.software.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderDetail 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer quantity;
	private float quotedprice;
	
	
	@OneToOne
	private Book book;
	@ManyToOne
	private Orders orders;
	
	public OrderDetail() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(Integer id, Integer quantity, float quotedprice, Book book, Orders orders) 
	{
		super();
		this.id = id;
		this.quantity = quantity;
		this.quotedprice = quotedprice;
		this.book = book;
		this.orders = orders;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}

	public float getQuotedprice() 
	{
		return quotedprice;
	}

	public void setQuotedprice(float quotedprice) 
	{
		this.quotedprice = quotedprice;
	}

	public Book getBook() 
	{
		return book;
	}

	public void setBook(Book book) 
	{
		this.book = book;
	}

	public Orders getOrders() 
	{
		return orders;
	}

	public void setOrders(Orders orders) 
	{
		this.orders = orders;
	}
	
	
	
	
	
}
