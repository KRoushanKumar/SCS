package com.manit.scs.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manit.scs.demo.dao.RoleDao;
import com.manit.scs.demo.dao.UserDao;
import com.manit.scs.demo.dao.UserRepo;
import com.manit.scs.demo.entity.Role;
import com.manit.scs.demo.entity.User;
import com.manit.scs.demo.user.BarChartOfMCA;
import com.manit.scs.demo.user.CrmUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
		
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_STUDENT")));
		user.setYear(crmUser.getYear());
		user.setDepartmentId(crmUser.getDepartmentId());
		
		user.setImage(crmUser.getImage());
		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User saveByRet(CrmUser crmUser) {
		
		User user = new User();
		 // assign user details to the user object
		user.setId(crmUser.getId());
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_STUDENT"),roleDao.findRoleByName("ROLE_USER")));
		//user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
		user.setYear(crmUser.getYear());
		user.setDepartmentId(crmUser.getDepartmentId());
		user.setImage(crmUser.getImage());
		user.setScholarNo(crmUser.getUserName());
		 // save user in the database
		//userDao.save(user);
		return userRepo.save(user);
	}

	@Override
	public List<User> MyClassmates(int year,String department) {
				return userRepo.MyClassmates(year,department) ;
	}

	@Override
	public User findByScholarId(String scholarId) {
		
		return userRepo.findByScholarId(scholarId);
	}

	/*
	 * @Override public List<BarChartOfMCA> findYearWiseStudents() {
	 * 
	 * return userRepo.findYearWiseStudents(); }
	 */
}
