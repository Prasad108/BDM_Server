package com.app.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
    @Query(" SELECT u FROM User u WHERE u.center in ( SELECT c.id from User u, Center c WHERE u.center=c.id and u.username= :username )")
	public List<User> allUserOfcurrentUsersCenter(@Param("username") String username);

}
