package com.example.demo.model;

public class MyModel {
	
	private String merchantId;
	private String cardNo;
	private String expiryDate;
	private String cvv;
	private String pin;
	private String bill;
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "MyModel [merchantId=" + merchantId + ", cardNo=" + cardNo + ", expiryDate=" + expiryDate + ", cvv="
				+ cvv + ", pin=" + pin + ", bill=" + bill + "]";
	}
	
}
