package com.manit.scs.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manit.scs.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{


	
	String query="SELECT * FROM user where year=:year and department=:department";
	@Query(value = query,nativeQuery = true)
	List<User> MyClassmates(@Param("year")  int year,@Param("department") String department);
	
	String query2="select * from user where scholar_no=:scholar_no";
	@Query(value = query2,nativeQuery = true)
	User findByScholarId(@Param("scholar_no") String scholar_no);
	
	/*
	 * String
	 * query3="select concat(first_name,\" \",last_name) as name from user where Id=:id;"
	 * ;
	 * 
	 * @Query(value = query3,nativeQuery = true) String getNameById(@Param("id") int
	 * senderId);
	 */
	
	
	
}
