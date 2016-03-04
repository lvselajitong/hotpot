package com.mycms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycms.model.Account;
import com.mycms.repository.account.AccountRepository;
import com.mycms.service.AccountService;
import com.mycms.repository.account.AccountRepository;
import com.mycms.response.*;
@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;
	@RequestMapping(value = "/login", method = {RequestMethod.POST} )  
	public ResponseData<Account> login(@RequestParam(value="username") String name, @RequestParam(value="password") String password) {
	      Account ac = accountRepository.findByName(name);
	      if(ac!=null && ac.getPassword().equals(password)){
	    	  return ResponseData.successData(ac);
	      }else{
	    	  return ResponseData.errorData("authenticate failed");
	      }
	}
	@RequestMapping(value = "/register", method = {RequestMethod.POST} )
	public ResponseData<Boolean> register(@RequestParam(value="username") String name, @RequestParam(value="password") String password, @RequestParam(value="email") String email){
		if(!StringUtils.isEmpty(name)&& accountRepository.findByName(name)== null){
			Account ac = new Account(name,password,email);
			ac = accountRepository.save(ac);
			return ResponseData.successData(true);
		}else{
			 return ResponseData.errorData("The user is already registered");
		}
	}

}
