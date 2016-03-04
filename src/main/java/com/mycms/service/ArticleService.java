package com.mycms.service;

import java.util.List;

import com.mycms.exception.ArticleNotFoundException;
import com.mycms.model.Article;
import com.mycms.model.Comment;



public interface ArticleService {
	List<Article> findAll();

	Article findOne(long id) throws ArticleNotFoundException;
	
	List<Article> findByStatus(String status) throws ArticleNotFoundException;

	Article update(Article article) throws ArticleNotFoundException;

	Article add(String title,String content,String status)throws ArticleNotFoundException; 
	
	void deleteById(long id);
	
	Article addCommentsToArticle(Article article,List<Comment> comments) throws ArticleNotFoundException;

}
