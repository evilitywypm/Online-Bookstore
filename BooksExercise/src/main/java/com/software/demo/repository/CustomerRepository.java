package com.software.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	
}
