package com.manit.scs.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="message_demo")
public class MessageDemo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private long id;
	
	@Column(name="department")
 	private String department;
	
	@Column(name="sender_id")
 	private int sender_id;
	
	
	@Column(name="status")
 	private char status;
	
	@Column(name="text")
 	private String text;
	
	@Column(name="year")
 	private int year;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	

 	public MessageDemo()
 	{
 		
 	}
	
	
	public MessageDemo(String department, int sender_id, char status, String text, int year) {
		super();
		this.department = department;
		this.sender_id = sender_id;
		this.status = status;
		this.text = text;
		this.year = year;
	}

	


	public MessageDemo(long id, String department, int sender_id, char status, String text, int year) {
		super();
		this.id = id;
		this.department = department;
		this.sender_id = sender_id;
		this.status = status;
		this.text = text;
		this.year = year;
	}
	
	
	





	public MessageDemo(long id, String department, int sender_id, char status, String text, int year, User user) {
		super();
		this.id = id;
		this.department = department;
		this.sender_id = sender_id;
		this.status = status;
		this.text = text;
		this.year = year;
		this.user = user;
	}

	

	public User getUser() {
		return user;
	}

	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUserName()
	{
		//return "roushan";
		if(getUser()!=null) {
		System.out.println(getUser().toString());
		return user.getFirstName();
		}
		else
		{
			return "NULL";
		}
			
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
 	
 	
}
