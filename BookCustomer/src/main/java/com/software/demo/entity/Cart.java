package com.software.demo.entity;

public class Cart extends Book 
{
	
	private int quantity;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, String title, String isbn, float price, String img, String file, String description,
			Author author, Category category) {
		super(id, title, isbn, price, img, file, description, author, category);
		// TODO Auto-generated constructor stub
	}

	public Cart(int quantity) {
		super();
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
}
