package com.software.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer balance;
	private String cardname;
	private Integer cardnum;
	private Integer cvv;
	private String expdate;
	private String paypalemail;
	
	public Bank() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Bank(Integer id, Integer balance, String cardname, Integer cardnum, Integer cvv, String expdate,
			String paypalemail) 
	{
		super();
		this.id = id;
		this.balance = balance;
		this.cardname = cardname;
		this.cardnum = cardnum;
		this.cvv = cvv;
		this.expdate = expdate;
		this.paypalemail = paypalemail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public Integer getCardnum() {
		return cardnum;
	}

	public void setCardnum(Integer cardnum) {
		this.cardnum = cardnum;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public String getPaypalemail() {
		return paypalemail;
	}

	public void setPaypalemail(String paypalemail) {
		this.paypalemail = paypalemail;
	}
	
	

}
