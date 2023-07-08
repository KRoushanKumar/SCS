package com.manit.scs.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manit.scs.demo.dao.ApplicationRepo;
import com.manit.scs.demo.entity.Application;
@Service
public class ApplicationImpl implements ApplicationService {

	private ApplicationRepo appRepo;
	
	
	
	public ApplicationImpl(ApplicationRepo appRepo) {
		super();
		this.appRepo = appRepo;
	}

	@Override
	public void save(Application application) {
		
		
		 appRepo.save(application);
	}

	@Override
	public List<Application> findAll() {
		
		return appRepo.findAll();
	}

	@Override
	public void approveApp(int id) {
		
		appRepo.aproveApp(id);
	}

	@Override
	public void rejectApp(int id) {
		appRepo.rejectApp(id);
	}

}
