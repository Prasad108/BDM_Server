package com.app.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUname(String username);
    Boolean existsByUname(String username);
    Boolean existsByEmail(String email);

}
