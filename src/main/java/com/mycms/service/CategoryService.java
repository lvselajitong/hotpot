package com.mycms.service;

import java.util.List;

import com.mycms.exception.CategoryDuplicateException;
import com.mycms.exception.CategoryNotFoundException;
import com.mycms.model.Category;

public interface CategoryService {
	
	List<Category> findAll();
	Category findOne(Long id) throws CategoryNotFoundException;
	Category add(String categoryName) throws CategoryDuplicateException;
	
	void deleteById(Long id) throws CategoryNotFoundException;

}
