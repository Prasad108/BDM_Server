package com.app.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.Role.Roles;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
    @Query(" SELECT u FROM User u WHERE u.center in ( SELECT c.id from User u, Center c WHERE u.center=c.id and u.username= :username )")
	public List<User> allUserOfcurrentUsersCenter(@Param("username") String username);
    
    @Query(" SELECT u FROM User u WHERE u.center.id = :id")
   	public List<User> getUsersOfCenterByCenterId(@Param("id") Integer id);
    
    @Modifying
    @Query(value = "update user u set name  = :name, email =:email, mob=:mob, role= :roles, counceller=:counceller  where id =:id", 
      nativeQuery = true)
    int updateUser(@Param("id") Integer id,@Param("name") String name,@Param("email") String email,@Param("mob") String mob,@Param("roles") Roles roles,@Param("counceller") String counceller);

}
