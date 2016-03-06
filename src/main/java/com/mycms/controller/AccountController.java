package com.mycms.controller;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycms.model.Account;
import com.mycms.model.Article;
import com.mycms.model.Category;
import com.mycms.repository.account.AccountRepository;
import com.mycms.repository.article.ArticleRepository;
import com.mycms.repository.category.CategoryRepository;
import com.mycms.service.AccountService;
import com.mycms.service.ArticleService;
import com.mycms.service.CategoryService;
import com.mycms.utils.HostUrl;
import com.mycms.repository.account.AccountRepository;
import com.mycms.response.*;
@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	@RequestMapping(value = "/login", method = {RequestMethod.POST} )  
	public ResponseData<Account> login(@RequestParam(value="username") String name, @RequestParam(value="password") String password) {
	      Account ac = accountRepository.findByName(name);
	      if(ac!=null && ac.getPassword().equals(password)){
	    	  return ResponseData.successData(ac);
	      }else{
	    	  return ResponseData.errorData("authenticate failed");
	      }
	}
	@RequestMapping(value = "/register", method = {RequestMethod.POST} )
	public ResponseData<Boolean> register(@RequestParam(value="username") String name, @RequestParam(value="password") String password, @RequestParam(value="email") String email){
		if(!StringUtils.isEmpty(name)&& accountRepository.findByName(name)== null){
			Account ac = new Account(name,password,email);
			ac = accountRepository.save(ac);
			return ResponseData.successData(true);
		}else{
			 return ResponseData.errorData("The user is already registered");
		}
	}
	@RequestMapping(value = "/postaritcle", method = {RequestMethod.POST} )  
	public ResponseData<Account> addArticle(@RequestParam String title, @RequestParam String content,@RequestParam(name="authId") Long authId, @RequestParam(name="postStatus") String postStatus,@RequestParam(name="category") String category) {
		try{
			Account auth = accountService.findOne(authId);
			Category cat = categoryRepository.findByCategoryName(category);
			if(auth != null){
				Date curDate = new Date();
				Article at = articleService.add(title, content, postStatus, curDate);
				if(at!=null){
					Set<Article> artSet = auth.getArticles();
					artSet.add(at);
					auth = accountRepository.save(auth);
					Set<Article> articleCatSet = cat.getArticles();
					articleCatSet.add(at);
					cat = categoryRepository.save(cat);
					return ResponseData.successData(auth);
				}else{
					return ResponseData.errorData("Fail to add article");
				}
			} else {
				return ResponseData.errorData(HttpStatus.BAD_REQUEST.value(),"Add article failed! Auth Id has error");
			}
		}catch (Exception e){
			e.printStackTrace();
			return ResponseData.errorData(HttpStatus.BAD_REQUEST.value(), "Exception found: " + e.getMessage());
		}
	}
	@RequestMapping(value="/article/{authId}")
	public ResponseData<Account> getArticlesById(@PathVariable Long authId) {
		try{
			Account auth = accountRepository.findOne(authId);
			if(auth != null) {
				return ResponseData.successData(auth);
			}else{
				return ResponseData.errorData("Fail to query articles by authId");
			}
		}catch (Exception e){
			e.printStackTrace();
			return ResponseData.errorData(HttpStatus.BAD_REQUEST.value(), "Exception found: " + e.getMessage());
		}
	}

}
