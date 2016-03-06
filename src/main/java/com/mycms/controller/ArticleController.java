package com.mycms.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycms.model.Account;
import com.mycms.model.Article;
import com.mycms.model.CommentStatus;
import com.mycms.model.PostStatus;
import com.mycms.repository.account.AccountRepository;
import com.mycms.repository.article.ArticleRepository;
import com.mycms.response.ResponseData;
import com.mycms.service.AccountService;
import com.mycms.service.ArticleService;

@RestController
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value="/articles",method={RequestMethod.GET} )
	public ResponseData<List<Article>> getAllArticles(){
		List<Article> articles =articleService.findAll();
		for(int i = 0; i<articles.size();i++){
			if(articles.get(i).getStatus().equals("Draft")){
				articles.remove(i);
			}
		}
		if(articles != null){
			return ResponseData.successData(articles);
		}else{
			return ResponseData.errorData("No articles");
		}
	}
	
	@RequestMapping(value="/articles/{id}",method={RequestMethod.POST})
	public ResponseData<Article> update(@PathVariable("id") String id,@RequestBody Article article){
		return null;
	}
	@RequestMapping(value="/articles/{articleId}/auth/{authId}",method={RequestMethod.DELETE})
	public ResponseData delete(@PathVariable("articleId") Long articleId,@PathVariable("authId") Long authId) {
		try{
			Account auth = accountService.findOne(authId);
			Article art = articleService.findOne(articleId);
			if(auth != null){
				Set<Article> artSet = auth.getArticles();
				artSet.remove(art);
				accountRepository.save(auth);
				articleService.deleteById(articleId);

				return ResponseData.successData(true);
			}else{
				return ResponseData.errorData("Fail to delete your article");
			}
		}catch (Exception e){
			e.printStackTrace();
			return ResponseData.errorData(HttpStatus.BAD_REQUEST.value(), "Exception found: " + e.getMessage());
		}
	}
}
