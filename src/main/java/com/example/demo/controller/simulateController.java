package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AccountRepo;
import com.example.demo.dao.CardRepo;
import com.example.demo.dao.TransactionsRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.MyModel;
import com.example.demo.model.MyModelDebit;
import com.example.demo.model.Response;
import com.example.demo.model.Transactions;
import com.example.demo.model.account;
import com.example.demo.model.card;

@RestController
public class simulateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(simulateController.class);

	@Autowired
	CardRepo cardRepo;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	TransactionsRepo transactionsRepo;

	@PostMapping(path = "/paymentSimulator")
	public String hit(@RequestBody MyModel model) {

		Response res = new Response();

		LOGGER.info("this is in bank simulator");
		String merchantId = model.getMerchantId();
		String cardNo = model.getCardNo();
		String expiryDate = model.getExpiryDate();
		String cvv = model.getCvv();
		String pin = model.getPin();
		String bill = model.getBill();

		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
		LocalDateTime localDateTime = LocalDateTime.now();
		String ldtString = FOMATTER.format(localDateTime);

		card userCardObj = cardRepo.validate(cardNo, expiryDate, cvv, pin);
		account merchantAccountObj = accountRepo.findByUserId(merchantId);

		if (userCardObj == null) {
			res.setResponseCode("500");
			res.setResponseMessage("Card details does not match");

			return res.getResponseCode() + "_" + res.getResponseMessage();
		}

		String userAccountNo = userCardObj.getAccount().getAccountNo();
		String merchantAccountNo = merchantAccountObj.getAccountNo();

		Transactions t = new Transactions();

		Random rand = new Random();
		String otp = Integer.toString(rand.nextInt(999999));
		t.setOtp(otp);
		t.setOtpStatus("active");
		t.setTransactionStatus("pending");
		t.setSenderAccountNo(userAccountNo);
		t.setReceiverAccountNo(merchantAccountNo);
		t.setPayment(bill);
		t.setDate(ldtString);
		t.setSenderName(userCardObj.getHolderName());
		res.setResponseCode("250");
		res.setResponseMessage("enter otp");
		transactionsRepo.save(t);
		return res.getResponseCode() + "_" + res.getResponseMessage() + "_" + t.getTransactionId();
	}

	@PostMapping("/debitCredit")
	public String debitCredit(@RequestBody MyModelDebit model) {
		Response res = new Response();
		String transactionId = model.getTransactionId();
		String otp = model.getOtp();

		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
		LocalDateTime localDateTime = LocalDateTime.now();
		String ldtString = FOMATTER.format(localDateTime);

		Transactions t = transactionsRepo.validate(Integer.parseInt(transactionId), otp);

		if (t == null) {
			res.setResponseCode("400");
			res.setResponseMessage("Wrong OTP");
			Transactions totp = transactionsRepo.findByTransactionId(Integer.parseInt(transactionId));
			totp.setOtpStatus("deactive");
			totp.setTransactionStatus("failed");
			totp.setDate(ldtString);
			transactionsRepo.save(totp);
			return res.getResponseCode() + "_" + res.getResponseMessage() + "_" + totp.getSenderName() + "_"
					+ totp.getTransactionId() + "_" + totp.getSenderAccountNo() + "_" + totp.getPayment() + "_"
					+ totp.getDate();
		}

		String userAccountNo = t.getSenderAccountNo();
		String merchantAccountNo = t.getReceiverAccountNo();
		String payment = t.getPayment();

		String userAccountBalance = accountRepo.findByAccountNo(userAccountNo).getAccountBalance();
		String merchantAccountBalance = accountRepo.findByAccountNo(merchantAccountNo).getAccountBalance();

		int userBal = Integer.parseInt(userAccountBalance);
		int merchantBal = Integer.parseInt(merchantAccountBalance);
		int amount = Integer.parseInt(payment);

		if (userBal < amount) {
			res.setResponseCode("300");
			res.setResponseMessage("Insufficient Funds");
			t.setOtpStatus("deactive");
			t.setTransactionStatus("failed");
			t.setDate(ldtString);
			transactionsRepo.save(t);
			return res.getResponseCode() + "_" + res.getResponseMessage() + "_" + t.getSenderName() + "_"
					+ t.getTransactionId() + "_" + t.getSenderAccountNo() + "_" + t.getPayment() + "_" + t.getDate();
		}
		userBal = userBal - amount;
		merchantBal = merchantBal + amount;

		String finalUserBalance = userBal + "";
		String finalMerchantBalance = merchantBal + "";

		accountRepo.findByAccountNo(userAccountNo).setAccountBalance(finalUserBalance);
		accountRepo.findByAccountNo(merchantAccountNo).setAccountBalance(finalMerchantBalance);

		accountRepo.save(accountRepo.findByAccountNo(userAccountNo));
		accountRepo.save(accountRepo.findByAccountNo(merchantAccountNo));

		res.setResponseCode("200");
		res.setResponseMessage("Success");

		t.setOtpStatus("deactive");
		t.setTransactionStatus("Success");
		t.setDate(ldtString);
		transactionsRepo.save(t);
		return res.getResponseCode() + "_" + res.getResponseMessage() + "_" + t.getSenderName() + "_"
				+ t.getTransactionId() + "_" + t.getSenderAccountNo() + "_" + t.getPayment() + "_" + t.getDate();

	}

}
