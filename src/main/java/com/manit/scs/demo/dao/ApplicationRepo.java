package com.manit.scs.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manit.scs.demo.entity.Application;

public interface ApplicationRepo extends JpaRepository<Application,Integer>{
 
	String query2="update application set status='A' where id=:id";
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = query2,nativeQuery = true)
	public void aproveApp(@Param("id") int id);
	
	
	String query3="update application set status='R' where id=:id";
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = query3,nativeQuery = true)
	public void rejectApp(@Param("id") int id);
}
