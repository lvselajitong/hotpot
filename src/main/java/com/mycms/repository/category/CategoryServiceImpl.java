package com.mycms.repository.category;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.mycms.exception.CategoryDuplicateException;
import com.mycms.exception.CategoryNotFoundException;
import com.mycms.model.Category;
import com.mycms.service.CategoryService;
@Repository
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> findAll() {
		List<Category> categories = Lists.newArrayList(categoryRepository.findAll());
		return categories;
	}

	@Override
	public Category add(String categoryName) throws CategoryDuplicateException {
		if(!StringUtils.isEmpty(categoryName)&& categoryRepository.findByCategoryName(categoryName)== null){
			Category cat = new Category(categoryName);
			cat = categoryRepository.save(cat);
			return cat;
		}else{
			throw new CategoryDuplicateException();
		}
	}

	@Override
	public void deleteById(Long id) throws CategoryNotFoundException {
		categoryRepository.delete(id);

	}
	@Override
	public Category findOne(Long id) throws CategoryNotFoundException {
		Category cat = categoryRepository.findOne(id);
		return cat;
	}

}
