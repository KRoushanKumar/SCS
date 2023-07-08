package com.manit.scs.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.manit.scs.demo.entity.User;
import com.manit.scs.demo.user.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
	public User saveByRet(CrmUser crmUser);
	
	public List<User> MyClassmates(int year,String Department);
	
	public User findByScholarId(String scholarId);
	
	
	//public List<BarChartOfMCA> findYearWiseStudents();
}
