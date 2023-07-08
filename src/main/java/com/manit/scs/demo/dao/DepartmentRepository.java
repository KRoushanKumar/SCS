package com.manit.scs.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manit.scs.demo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
