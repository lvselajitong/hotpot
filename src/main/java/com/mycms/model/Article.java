package com.mycms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="T_ARTICLE")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	protected Long id;
	@Column(name = "title", nullable = false,unique = true)
	protected String title;
	
	@Column(name="content",nullable = true)
	protected String content;
	
	@Column(name="status",nullable = false)
	protected String status;
	
	
	@Column(name = "comment_status")
	protected String commentStatus;
	
	@Column(name = "post_date")
	protected Date postDate;
	
	@Column(name = "modifed_date")
	protected Date modifiedDate;	
	
	@OneToMany
	protected List<Comment> comments;
	public Article() {

	}
	public Article(String title,String content,String status){
		this.title = title;
		this.content = content;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
