package com.mycms.repository.article;

import org.springframework.data.repository.CrudRepository;

import com.mycms.model.Article;

public interface ArticleRepository extends CrudRepository<Article,Long>{
	Article findByTitle(String title);
	
}
