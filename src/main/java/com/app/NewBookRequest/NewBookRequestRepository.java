package com.app.NewBookRequest;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.User.User;

public interface NewBookRequestRepository extends CrudRepository<NewBookRequest, Integer>{
	
	@Query("SELECT request FROM NewBookRequest request where request.user = :user")
	public List<NewBookRequest> getNewBookRequestsByUser(@Param("user") User user);
	
	@Modifying
	@Query("UPDATE NewBookRequest request set request.status=:status, request.remark=:remark where request.id=:requestId")
	public void updateRequestById(@Param("requestId") Integer requestId,@Param("status") String status,@Param("remark") String remark);

}
