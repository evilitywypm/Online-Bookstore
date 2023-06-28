package com.software.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.software.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	@Query("select c from Customer c where c.name=?1")
	Customer getUserByCustomername(String name);
	
	@Query("select  cc from Customer cc where cc.email =?1 and cc.password = ?2")
	Customer findByEmailandPassword(String email, String password);
	Customer findByEmail(String email);
	
	
	
	
}
