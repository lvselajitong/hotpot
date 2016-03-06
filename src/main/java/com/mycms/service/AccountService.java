package com.mycms.service;
import com.mycms.exception.AccountNotFoundException;
import com.mycms.model.Account;
import com.mycms.model.Article;

import java.util.List;
public interface AccountService {
	List<Account> findAll();

	Account findOne(long id) throws AccountNotFoundException;

	Account update(Account account) throws AccountNotFoundException;

	Account add(String name,String password,String email)throws AccountNotFoundException; 

	void deleteById(long id);
}
