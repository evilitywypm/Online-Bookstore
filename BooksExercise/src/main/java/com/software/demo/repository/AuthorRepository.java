package com.software.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	@Query("select a from Author a where a.name =?1")
	List<Author> findByName(String search);



}
