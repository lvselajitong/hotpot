package com.mycms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycms.model.Article;
import com.mycms.model.CommentStatus;
import com.mycms.model.PostStatus;
import com.mycms.repository.article.ArticleRepository;
import com.mycms.response.ResponseData;
import com.mycms.service.ArticleService;

@RestController
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping(value = "/articles", method = {RequestMethod.POST} )  
	public ResponseData<Article> addArticle(@RequestParam String title, @RequestParam String content,@RequestParam(name="postStatus") String postStatus) {

		Article at = articleService.add(title, content, postStatus);
		if(at!=null){
			return ResponseData.successData(at);
		}else{
			return ResponseData.errorData("Fail to add article");
		}
	}
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
	@RequestMapping(value="/articles/{id}",method={RequestMethod.DELETE})
	public ResponseData delete(@PathVariable("id") Long id) {
		articleService.deleteById(id);
		return ResponseData.successData(true);
	}
}
