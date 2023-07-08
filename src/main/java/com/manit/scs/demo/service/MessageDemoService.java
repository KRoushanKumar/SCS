package com.manit.scs.demo.service;

import java.util.List;

import com.manit.scs.demo.entity.MessageDemo;

public interface MessageDemoService {
	List<MessageDemo> getUserMessage(int year,String Depart);

	MessageDemo save(MessageDemo messageDemo);
	
	void addUserId(int UserId,Long Id);
}
