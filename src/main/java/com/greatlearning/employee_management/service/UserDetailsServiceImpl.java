package com.greatlearning.employee_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.employee_management.entity.Users;
import com.greatlearning.employee_management.repositories.UserRepository;
import com.greatlearning.employee_management.security.EmployeeUserDetail;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.getUserByUserName(username);

        if (user == null) {
        	throw new UsernameNotFoundException("Could not find user!!!");
        }

        return new EmployeeUserDetail(user);
    }
}


