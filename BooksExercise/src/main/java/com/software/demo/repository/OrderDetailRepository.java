package com.software.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> 
{
	@Query("select orders from OrderDetail orders where orders.orders.id=?1")
	public List<OrderDetail> findByOrderId(Integer id);
}
