package com.software.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> 
{

}
