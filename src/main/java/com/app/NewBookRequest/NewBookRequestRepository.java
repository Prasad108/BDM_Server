package com.app.NewBookRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Challan.Challan;

public interface NewBookRequestRepository extends CrudRepository<NewBookRequest, Integer>{
	
	@Query("SELECT request FROM NewBookRequest request where request.user = :username")
	public NewBookRequest[] getNewBookRequestsByUser(@Param("username") String username);

}
