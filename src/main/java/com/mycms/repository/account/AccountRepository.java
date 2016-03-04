package com.mycms.repository.account;

import org.springframework.data.repository.CrudRepository;

import com.mycms.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findByName(String name);
}
