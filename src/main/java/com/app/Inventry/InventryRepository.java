package com.app.Inventry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.BookName.BookName;
import com.app.Languages.Languages;
import com.app.Type.Type;

public interface InventryRepository extends CrudRepository<Inventry, Integer> {

	@Query("SELECT i , b FROM Book b, Inventry i, Center c, User u ,BookName bn, Type t, Languages l " + 
			"WHERE b.id=i.book " + 
			"AND b.name= bn.id " + 
			"AND b.languages= l.id " + 
			"AND b.type= t.id " + 
			"AND i.center= c.id " + 
			"AND c.id= u.center " + 
			"AND u.username= :name " + 
			"AND b.name= :Bname " + 
			"AND b.languages= :lang " + 
			"AND b.type= :type ")
	public Optional<List<Object[]>> findByUserCenterLangNameandType(@Param("Bname") BookName bn,@Param("lang") Languages l,@Param("type") Type t,@Param("name") String username);

}
