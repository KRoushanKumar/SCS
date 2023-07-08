package com.manit.scs.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groupchat")

public class GroupChat {
     @Id
     
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private long group_id;
 	
 	private String name;
 	private int d_id;
 	private int message_id;
 	private char status;
	public long getGroup_id() {
		return group_id;
	}
	
	
	
	public GroupChat(String name, int d_id, int message_id, char status) {
		super();
		this.name = name;
		this.d_id = d_id;
		this.message_id = message_id;
		this.status = status;
	}



	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
 	
 	
     
}
