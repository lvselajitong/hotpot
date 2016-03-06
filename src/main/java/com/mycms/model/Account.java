package com.mycms.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="T_ACCOUNT")
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	protected Long id;
	@Column(name = "name", nullable = false, unique = true)
	protected String name;
	@Column(name="password",nullable=false)
	protected String password;
	@Column(name = "email")
	protected String email;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	protected Set<Article> articles = new HashSet<Article>();
	
	public Account() {

	}

	
	public Account(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}




}
