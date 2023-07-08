package com.manit.scs.demo.dao;

import com.manit.scs.demo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
