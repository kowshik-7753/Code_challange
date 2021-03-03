package com.hcl.training.service;

import java.util.Optional;

import com.hcl.training.model.Account;
import com.hcl.training.model.Transfer;

public interface ServiceInf {

	String saveAccount(Account account);

	Optional<Account> getAccountByNumber(String number);

	String transferAmount(Transfer transfer);

	Optional<Account> getAccountById(int id);

}
