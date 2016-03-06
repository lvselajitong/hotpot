package com.mycms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="T_LABEL")
public class Label implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	protected Long id;

	@Column(name = "label_name", nullable = false, unique = true)
	protected String labelName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	public Label(){
		
	}

}
