package com.manit.scs.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manit.scs.demo.dao.MessageDemoRepo;
import com.manit.scs.demo.entity.MessageDemo;

@Service
public class MessageDemoServiceImpl implements MessageDemoService {

	private MessageDemoRepo messageDemoRepo;
	
	
	public MessageDemoServiceImpl(MessageDemoRepo messageDemoRepo) {
		super();
		this.messageDemoRepo = messageDemoRepo;
	}


	@Override
	public List<MessageDemo> getUserMessage(int year, String Depart) {
		
		return messageDemoRepo.getGroupChat(year, Depart);
	}


	@Override
	public MessageDemo save(MessageDemo messageDemo) {
		return messageDemoRepo.save(messageDemo);
		
	}
	
	public void addUserId(int UserId,Long id)
	{
		messageDemoRepo.setUserId(UserId, id);
	}

}
