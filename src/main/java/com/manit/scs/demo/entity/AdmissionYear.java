package com.manit.scs.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admission_year")
public class AdmissionYear {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int year;

	public AdmissionYear()
	{
		
	}
	
	
	
	public AdmissionYear(int id, int year) {
		super();
		this.id = id;
		this.year = year;
	}



	public AdmissionYear(int year) {
		super();
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	
}
