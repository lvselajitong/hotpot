package com.mycms.repository.category;

import org.springframework.data.repository.CrudRepository;

import com.mycms.model.Category;


public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findByCategoryName(String categoryName);
}
