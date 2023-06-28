package com.software.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Bank;
import com.software.demo.entity.Category;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer>
{
	@Query
	("select k from Bank k where k.cvv =?1 and k.cardnum =?2")
	Bank findByCvvcard(Integer cvv, Integer cardnum);
}
