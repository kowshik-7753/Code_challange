package com.hcl.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hcl.training.model.Account;
import com.hcl.training.model.Transfer;
import com.hcl.training.service.ServiceInf;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BankApplicationTests {
	Account account;
	Transfer transfer;
	@Autowired
	ServiceInf service;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setUp() {
		account = new Account();
		transfer = new Transfer();
	}

	@Test
	public void testAccount() {
		account.setAccountNumber("2123456712");
		account.setAccountHolder("abc");
		account.setBalance(10000);
		service.saveAccount(account);
	}

	@Test
	public void testAccountAlreadyExists() {
		account.setAccountNumber("2123456789");
		account.setAccountHolder("abc");
		account.setBalance(10000);
		service.saveAccount(account);
	}

	@Test
	public void testAccountwithwrongnumber() {
		account.setAccountNumber("112123456789");
		account.setAccountHolder("abc");
		account.setBalance(10000);
		service.saveAccount(account);
	}

	@Test
	public void testAccountByNumber() {
		service.getAccountByNumber("2123456789");
	}

	@Test
	public void testAccountByWrongNumber() {
		service.getAccountByNumber("2113456789");
	}

	@Test
	public void testAccountById() {
		service.getAccountById(1);
	}

	@Test
	public void testAccountByWrongId() {
		service.getAccountById(10);
	}

	@Test
	public void testTransferFund() {
		transfer.setToAccount("1234567890");
		transfer.setFromAccount("2123456789");
		transfer.setAmount(10);
		service.transferAmount(transfer);
	}

	@Test
	public void testTransferFundWithNosender() {
		transfer.setFromAccount("1243567890");
		transfer.setToAccount("2123456789");
		transfer.setAmount(5000);
		service.transferAmount(transfer);
	}

	@Test
	public void testTransferFundWithNoReciever() {
		transfer.setFromAccount("1234567890");
		transfer.setToAccount("1212131410");
		transfer.setAmount(5000);
		service.transferAmount(transfer);
	}

	@Test
	public void testTransferFundWithNLessFunds() {
		transfer.setFromAccount("1234567890");
		transfer.setToAccount("2123456789");
		transfer.setAmount(50000);
		service.transferAmount(transfer);
	}

	@Test
	public void testTransferFundWithNegitiveValue() {
		transfer.setFromAccount("1234567890");
		transfer.setToAccount("2123456789");
		transfer.setAmount(-50000);
		service.transferAmount(transfer);
	}

}
