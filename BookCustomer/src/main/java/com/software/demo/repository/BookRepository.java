package com.software.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.demo.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query
	("select b from Book b where b.title =?1")
	List<Book> findByTitle (String search);
	
	@Query
	("select b from Book b where b.category.id =?1")
	List<Book> findByCategory (Integer id);
	
	
	

}
