package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.user;

public interface UserRepo extends JpaRepository<user,String>{
	public user findByUserId(String id);
}
