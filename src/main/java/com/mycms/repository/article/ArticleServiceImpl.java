package com.mycms.repository.article;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.mycms.exception.ArticleDuplicateException;
import com.mycms.exception.ArticleNotFoundException;
import com.mycms.model.Article;
import com.mycms.model.Comment;
import com.mycms.service.ArticleService;
@Repository
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> findAll() {
		List<Article> articles = Lists.newArrayList(articleRepository.findAll());
		return articles;
	}

	@Override
	public Article findOne(long id) throws ArticleNotFoundException {
		Article article = articleRepository.findOne(id);
		return article;
	}
	
	@Override
	public List<Article> findByStatus(String status) throws ArticleNotFoundException {
		return null;
	}

	@Override
	public Article update(Article article) throws ArticleNotFoundException {
		Article at = articleRepository.save(article);
		return at;
	}

	@Override
	public Article add(String title, String content, String status) throws ArticleNotFoundException {
		if(!StringUtils.isEmpty(title)&& articleRepository.findByTitle(title)== null){
			Article at = new Article(title,content,status);
			at = articleRepository.save(at);
			return at;
		}else{
			throw new ArticleDuplicateException();
		}
		
	}

	@Override
	public void deleteById(long id) {
		articleRepository.delete(id);

	}

	@Override
	public Article addCommentsToArticle(Article article, List<Comment> comments) throws ArticleNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
