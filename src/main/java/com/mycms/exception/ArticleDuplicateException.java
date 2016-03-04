package com.mycms.exception;

public class ArticleDuplicateException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public ArticleDuplicateException(){
		super("Article duplicate");
	}
}
