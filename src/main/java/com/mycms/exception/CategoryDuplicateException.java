package com.mycms.exception;

public class CategoryDuplicateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CategoryDuplicateException(){
		super("Category duplicate");
	}
}
