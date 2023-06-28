package com.software.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Income;
import com.software.demo.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> 
{
	@Query("select od.orderdate as orderDate, sum(od.totalprice) as totalPrice from Orders od where od.status = 'confirm' group by od.orderdate")
	List<Income> findByDailyIncome();
	
	@Query("select year(od.orderdate) as year, month(od.orderdate) as month, sum(od.totalprice) as totalPrice from Orders od where od.status = 'confirm' group by month(od.orderdate)")
	List<Income> findByMonthlyIncome();
	
	@Query("select year(od.orderdate) as year, sum (od.totalprice) as totalPrice from Orders od where od.status = 'confirm' group by year(od.orderdate)")
	List<Income> findByYearlyIncome();
}
