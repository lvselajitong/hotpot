package com.mycms.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String accountId) {
		super("No such account: " + accountId);
	}
}
