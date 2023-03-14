package com.company.registeruser.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.registeruser.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
	
	public User findByName(String name);
//		
//	 @Query(value = "SELECT n FROM User n WHERE n.email=:email")
//	    public List<User> findByUserEmail(@Param("email") String email);
	
	
    @Query("SELECT u FROM User u WHERE u.name =:name")
    Optional<User> buscarUsuarioPorName(String name);
	
	  Boolean existsByName(String name);

	  Boolean existsByEmail(String email);
	 

}
