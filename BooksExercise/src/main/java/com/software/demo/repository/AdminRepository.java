package com.software.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query("select  a from Admin a where a.email =?1 and a.password = ?2")
	Admin findByEmailandPassword(String email, String password);
	
	
}

