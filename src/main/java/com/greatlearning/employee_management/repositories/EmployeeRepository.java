package com.greatlearning.employee_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greatlearning.employee_management.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstname) LIKE %:firstname%")
    List<Employee> findEmployeeByFirstname(@Param("firstname") String firstname);
//    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findAllByOrderByFirstnameAsc();

    List<Employee> findAllByOrderByFirstnameDesc();
}
