package com.mycms.exception;

public class CategoryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public CategoryNotFoundException(){
		super("Category not found");
	}
}
