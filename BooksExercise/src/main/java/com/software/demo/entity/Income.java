package com.software.demo.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public interface Income 
{
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date getOrderDate();
	Integer getTotalPrice();
	
	String getYear();
	String getMonth();
	
}
