package com.mycms.exception;

public class AccountDuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public AccountDuplicateException(){
		super("Account duplicate");
	}

}
