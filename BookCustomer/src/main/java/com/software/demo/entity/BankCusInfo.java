package com.software.demo.entity;

public class BankCusInfo 
{
	private Bank bank;
	private CustomerInfo cusfinfo;
	
	public BankCusInfo() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public BankCusInfo(Bank bank, CustomerInfo cusfinfo) 
	{
		super();
		this.bank = bank;
		this.cusfinfo = cusfinfo;
	}

	public Bank getBank() 
	{
		return bank;
	}

	public void setBank(Bank bank) 
	{
		this.bank = bank;
	}

	public CustomerInfo getCusfinfo() 
	{
		return cusfinfo;
	}

	public void setCusfinfo(CustomerInfo cusfinfo) 
	{
		this.cusfinfo = cusfinfo;
	}
	
	
}
