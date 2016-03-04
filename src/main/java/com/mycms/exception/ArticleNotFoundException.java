package com.mycms.exception;

public class ArticleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ArticleNotFoundException(String title) {
		super("No such article: " + title);
	}
}
