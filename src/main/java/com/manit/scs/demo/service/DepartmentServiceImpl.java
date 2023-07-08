package com.manit.scs.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manit.scs.demo.dao.DepartmentRepository;
import com.manit.scs.demo.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	DepartmentRepository departmentRepository;
	
	
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}



	@Override
	public List<Department> findAll() {
		
		return departmentRepository.findAll();
	}

}
