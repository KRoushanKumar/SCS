package com.manit.scs.demo.service;

import java.util.List;


import com.manit.scs.demo.entity.Application;

public interface ApplicationService {
	
 public void save(Application application);
 
 public List<Application> findAll();

 public  void approveApp(int id) ;
 public  void rejectApp(int id) ;
}
