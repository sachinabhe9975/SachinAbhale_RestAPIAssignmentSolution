package com.greatlearning.employee_management.service;

import java.util.List;

import com.greatlearning.employee_management.entity.Employee;
import com.greatlearning.employee_management.entity.Roles;
import com.greatlearning.employee_management.entity.Users;


public interface EmployeeService {
	
	 public List<Employee> findAllEmployees();

	    public Employee findEmployeeById(int id);

	    public void saveEmployee(Employee employee);

	    public void deleteEmployeeById(int id);

	    public List<Employee> searchEmployeeByFirstName(String firstName);

	    public List<Employee> sortEmployeeByOrder(String order);

	    public Users saveUser(Users user);

	    public Roles saveRole(Roles role);
}