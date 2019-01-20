package com.app.CbDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.BookName.BookName;
import com.app.Languages.Languages;
import com.app.Type.Type;

public interface CbDetailsRepository extends CrudRepository<CbDetails, Integer> {
	
	@Query("SELECT cb FROM CbDetails cb, Book b, Challan c " + 
			"WHERE cb.book=b.id " + 
			"AND cb.challan=c.id " + 
			"AND b.name= :name " + 
			"AND b.languages= :lang " + 
			"AND b.type= :type " + 
			"AND c.id= :challan")
	public Optional<CbDetails> getCbDetailFromChallanWithRequestedNameLangType(@Param("challan") Integer challan,@Param("name") BookName name,@Param("lang") Languages lang,@Param("type") Type type);

}
