package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class user{
	
	@OneToMany(mappedBy="user")
    private List<account> account=new ArrayList<account>();
	@Id
	private String userId;
	private String userName;
	private String userType;
	
	public List<account> getAccount() {
		return account;
	}
	public void setAccount(List<account> account) {
		this.account = account;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "user [userId=" + userId + ", userName=" + userName + ", userType=" + userType + "]";
	}
}
