package com.manit.scs.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application")

public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name="body")
	private String body;
	
	@Column(name = "date")
	private String data;
	
	@Column(name = "status")
	private char status;

	public Application()
	{
		
	}
	
	public Application(String subject, String body, String data, char status) {
		super();
		this.subject = subject;
		this.body = body;
		this.data = data;
		this.status = status;
	}
	
	

	public Application(Integer id, String subject, String body, String data, char status) {
		super();
		this.id = id;
		this.subject = subject;
		this.body = body;
		this.data = data;
		this.status = status;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", subject=" + subject + ", body=" + body + ", data=" + data + ", status="
				+ status + "]";
	}
	
	
	
	
}
