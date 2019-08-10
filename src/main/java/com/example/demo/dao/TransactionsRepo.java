package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Transactions;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {

	@Query(value = "SELECT * FROM Transactions t WHERE t.transaction_id=?1 and t.otp=?2 and t.otp_status='active' and t.transaction_status='pending' ", nativeQuery = true)
	public Transactions validate(int transactionId, String otp);

	@Query(value = "SELECT * FROM Transactions t WHERE t.transaction_id=?1 and t.otp_status='active' and t.transaction_status='pending' ", nativeQuery = true)
	public Transactions findByTransactionId(int transactionId);

}
