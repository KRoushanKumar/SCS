package com.manit.scs.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long d_id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private char status;
	

	
	public Department()
	{
		
	}
	
	public Department(String name, char status) {
		super();
		this.name = name;
		this.status = status;
	}
	
	

	public long getD_id() {
		return d_id;
	}
	public void setD_id(long d_id) {
		this.d_id = d_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}


	
	
	
}
