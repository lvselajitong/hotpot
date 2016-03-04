package com.mycms.repository.account;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import com.mycms.exception.AccountNotFoundException;
import com.mycms.exception.AccountDuplicateException;
import com.mycms.model.Account;
import com.mycms.service.AccountService;
import org.springframework.util.StringUtils;


@Repository
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public List<Account> findAll() {
		List<Account> accounts = Lists.newArrayList(accountRepository.findAll());
		return accounts;
	}

	@Override
	public Account findOne(long id) throws AccountNotFoundException {
		Account account = accountRepository.findOne(id);
		return account;
	}

	@Override
	public Account update(Account account) throws AccountNotFoundException {
		Account ac = accountRepository.save(account);
		return ac;
	}

	@Override
	public Account add(String name, String password, String email) throws AccountNotFoundException {
		if(!StringUtils.isEmpty(name)&& accountRepository.findByName(name)== null){
			Account ac = new Account(name,password,email);
			ac = accountRepository.save(ac);
			return ac;
		}else{
			throw new  AccountDuplicateException();
		}
	}

	@Override
	public void deleteById(long id) {
		accountRepository.delete(id);
	}

}
