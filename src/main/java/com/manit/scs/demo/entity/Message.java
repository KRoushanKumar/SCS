package com.manit.scs.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")

public class Message {
	 	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private long m_id;
	 	
	 	private String message_id;
	 	private String text;
	 	private int sender_id;
	 	private int receiver_id;
	 	private char status;
	 	
		public Message(String text, int sender_id, int receiver_id, char status) {
			super();
			this.text = text;
			this.sender_id = sender_id;
			this.receiver_id = receiver_id;
			this.status = status;
		}
		public Message() {
			
		}
		public long getM_id() {
			return m_id;
		}
		public void setM_id(long m_id) {
			this.m_id = m_id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public char getStatus() {
			return status;
		}
		public void setStatus(char status) {
			this.status = status;
		}
		public int getSenderId() {
			return sender_id;
		}
		public void setSenderId(int senderId) {
			this.sender_id = senderId;
		}
		public int getReceiverId() {
			return receiver_id;
		}
		public void setReceiverId(int receiverId) {
			this.receiver_id = receiverId;
		}
		public String getMessage_id() {
			return message_id;
		}
		public void setMessage_id(String message_id) {
			this.message_id = message_id;
		}
		public int getSender_id() {
			return sender_id;
		}
		public void setSender_id(int sender_id) {
			this.sender_id = sender_id;
		}
		public int getReceiver_id() {
			return receiver_id;
		}
		public void setReceiver_id(int receiver_id) {
			this.receiver_id = receiver_id;
		}
	 	
		
	 	
}
