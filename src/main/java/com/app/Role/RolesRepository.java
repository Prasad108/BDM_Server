package com.app.Role;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Integer> {
	Optional<Roles> findByRole(String roleName);
}
