package com.app.BookName;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BookNameRepository extends CrudRepository<BookName, Integer> {

	@Query("SELECT DISTINCT bn FROM User u, Center c, Book b, Inventry i ,BookName bn WHERE u.center=c.id AND c.id=i.center AND b.id=i.book AND bn.id=b.name AND u.username= :username") 
	public Optional<List<BookName>> getAllBookNameOfUsersInventory(@Param("username") String username);
}
