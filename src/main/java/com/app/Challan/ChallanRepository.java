package com.app.Challan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChallanRepository extends CrudRepository<Challan, Integer> {
	
	public List<Challan> findByUserByIssuedTo_Id(Integer id);
	
	@Query("SELECT ch FROM Challan ch WHERE ch.userByIssuedTo in ( SELECT u.id FROM User u WHERE u.center in ( SELECT c.id from User u, Center c WHERE u.center=c.id and u.username= :username ) )") 
	public Challan[] getAllChallanOfUsersCenter(@Param("username") String username);
	
	@Query("SELECT ch FROM Challan ch WHERE ch.userByIssuedTo in ( SELECT u.id FROM User u WHERE u.center in ( SELECT c.id from User u, Center c WHERE u.center=c.id and u.username= :username ) )")
	public Challan[] justTry(@Param("username") String username);

	@Query("SELECT ch FROM Challan ch WHERE ch.userByIssuedTo in (SELECT u.id FROM User u where u.username=:username)")
	public Challan[] getListOfUserSpecificChallan(@Param("username") String username);
	
	@Query("SELECT ch FROM Challan ch WHERE ch.id = :id and ch.settled = 1")
	public Optional<Challan> checkIfChallanIsSettled(@Param("id") Integer id);
}
