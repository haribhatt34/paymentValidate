package com.example.demo.model;

public class MyModelDebit {
	
	private String transactionId;
	private String otp;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "MyModelDebit [transactionId=" + transactionId + ", otp=" + otp + "]";
	}
	
}
