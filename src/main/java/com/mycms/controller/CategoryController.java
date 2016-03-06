package com.mycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycms.model.Category;
import com.mycms.repository.category.CategoryRepository;
import com.mycms.response.ResponseData;
import com.mycms.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value="/category",method={RequestMethod.POST})
	public ResponseData<Category> addCategory(@RequestParam String categoryName) {
		try{
			Category cat = categoryService.add(categoryName);
			if(cat != null){
				return  ResponseData.successData(cat);
			}else{
				return ResponseData.errorData("Fail to add category");
			}
		
		}catch (Exception e){
			e.printStackTrace();
			return ResponseData.errorData(HttpStatus.BAD_REQUEST.value(), "Exception found: " + e.getMessage());
		}
	}
	
	@RequestMapping(value="/category",method={RequestMethod.GET})
	public ResponseData<List<Category>> getAllCategories(){
		try{
			List<Category> cats = categoryService.findAll();
			if(cats != null){
				return ResponseData.successData(cats);
			}else{
				return ResponseData.errorData("Fail to query categories");
			}
		}catch (Exception e){
			e.printStackTrace();
			return ResponseData.errorData(HttpStatus.BAD_REQUEST.value(), "Exception found: " + e.getMessage());
		}
	}
	@RequestMapping(value="/category",method={RequestMethod.DELETE})
	public ResponseData deleteCategory(){
		return null;
	}

}
