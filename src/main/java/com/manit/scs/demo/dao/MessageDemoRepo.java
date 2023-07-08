package com.manit.scs.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manit.scs.demo.entity.MessageDemo;

public interface MessageDemoRepo extends JpaRepository<MessageDemo, Long>{


	
	String query="SELECT * FROM message_demo where year=:year and department=:department order by id desc";
	@Query(value = query,nativeQuery = true)
	List<MessageDemo> getGroupChat(@Param("year")  int year,@Param("department") String department);

	String query2="update message_demo set user_id=:user_id where id=:id";
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = query2,nativeQuery = true)
	void setUserId(@Param("user_id") int user_id,@Param("id") Long id);
}
