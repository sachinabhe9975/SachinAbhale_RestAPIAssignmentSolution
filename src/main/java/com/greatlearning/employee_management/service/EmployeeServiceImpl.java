package com.greatlearning.employee_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employee_management.entity.Employee;
import com.greatlearning.employee_management.entity.Roles;
import com.greatlearning.employee_management.entity.Users;
import com.greatlearning.employee_management.repositories.EmployeeRepository;
import com.greatlearning.employee_management.repositories.RoleRepository;
import com.greatlearning.employee_management.repositories.UserRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {

       Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }

        Employee foundEmployee =  employeeOptional.get();
        return foundEmployee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> searchEmployeeByFirstName(String firstName) {
        return employeeRepository.findEmployeeByFirstname(firstName.toLowerCase());
    }

    @Override
    public List<Employee> sortEmployeeByOrder(String order) {

        List<Employee> employees = null;

        if (order.toLowerCase().equals("asc")) {
            employees = employeeRepository.findAllByOrderByFirstnameAsc();
        } else if (order.toLowerCase().equals("desc")) {
            employees = employeeRepository.findAllByOrderByFirstnameDesc();
        } else {
            throw new RuntimeException("No proper order mentioned");
        }
        return employees;
    }

    @Override
    public Users saveUser(Users user) {
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Roles saveRole(Roles role) {
        return roleRepository.save(role);
    }

	

	
}
