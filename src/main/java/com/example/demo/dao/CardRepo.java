package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.card;

public interface CardRepo extends JpaRepository<card,String>{
	
	public card findByCardNo(String cardNo);
	
	@Query(value = "SELECT * FROM card c WHERE c.card_no=?1 and c.expire_date=?2 and c.cvv=?3 and c.pin=?4",nativeQuery = true)
	public card validate(String cardNo,String expiryDate,String cvv,String pin);
}
