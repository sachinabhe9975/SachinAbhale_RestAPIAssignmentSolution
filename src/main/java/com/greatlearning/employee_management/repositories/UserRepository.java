package com.greatlearning.employee_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.employee_management.entity.Users;



public interface UserRepository extends JpaRepository<Users, Integer> {
	@Query("from Users u where u.username=?1")
	public Users getUserByUserName(String username);
 
}

