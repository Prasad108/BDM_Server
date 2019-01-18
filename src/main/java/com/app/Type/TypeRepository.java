package com.app.Type;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TypeRepository extends CrudRepository<Type, Integer> {

	@Query("SELECT DISTINCT t FROM User u, Center c, Book b, Inventry i ,BookName bn, Languages l, Type t WHERE u.center=c.id AND c.id=i.center AND b.id=i.book AND bn.id=b.name AND l.id=b.languages AND t.id=b.type AND u.username= :username AND bn.id= :bookNameId AND l.id= :langId")
	public List<Type> getAllTypesForBookNameAndLanguageInUsersInventory(@Param("bookNameId") Integer bookNameId, @Param("langId") Integer langId, @Param("username") String username);

}
