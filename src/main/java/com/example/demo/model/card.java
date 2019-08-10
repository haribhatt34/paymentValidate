package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class card {
	
	@Id
	private String cardNo;
	private String holderName;
	private String cvv;
	private String expireDate;
	private String pin;
	
	@ManyToOne
	private account account;
	
	public account getAccount() {
		return account;
	}
	public void setAccount(account account) {
		this.account = account;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "card [cardNo=" + cardNo + ", holderName=" + holderName + ", cvv=" + cvv + ", expireDate=" + expireDate
				+ ", pin=" + pin + ", account=" + account + "]";
	}
	
	
}
