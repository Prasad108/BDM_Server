package com.app.Languages;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface LanguagesRepository extends CrudRepository<Languages, Integer> {
	@Query("SELECT DISTINCT l FROM User u, Center c, Book b, Inventry i ,BookName bn, Languages l WHERE u.center=c.id AND c.id=i.center AND b.id=i.book AND bn.id=b.name AND l.id=b.languages AND u.username= :username AND bn.id= :bookNameId") 
	public Optional<List<Languages>> getAllBookNameOfUsersInventory(@Param("bookNameId") Integer bookNameId,@Param("username") String username);

}
