package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class account {
	
	@Id
	private String accountNo;
	private String phoneNo;
	private String accountBalance;
	private String accountType;
	
	@ManyToOne
	private user user;
	
	@OneToMany(mappedBy="account")
	private List<card> card=new ArrayList<card>();
	
	
	public List<card> getCard() {
		return card;
	}
	public void setCard(List<card> card) {
		this.card = card;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "account [accountNo=" + accountNo + ", phoneNo=" + phoneNo + ", accountBalance=" + accountBalance
				+ ", accountType=" + accountType + ", user=" + user + ", card=" + card + "]";
	}

}
	
