package com.app.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByName(String username);
    Boolean existsByName(String username);
    Boolean existsByEmail(String email);

}
