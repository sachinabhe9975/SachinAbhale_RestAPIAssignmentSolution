package com.greatlearning.employee_management.controller;



import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.employee_management.entity.Employee;
import com.greatlearning.employee_management.entity.Roles;
import com.greatlearning.employee_management.entity.Users;
import com.greatlearning.employee_management.repositories.EmployeeRepository;
import com.greatlearning.employee_management.service.EmployeeService;



@RestController

public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/user")
    public Users saveUser(@RequestBody Users user) {
        return employeeService.saveUser(user);
    }

    @PostMapping("/role")
    public Roles saveRole(@RequestBody Roles role) {
        return employeeService.saveRole(role);
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
        System.out.println(currentPrincipalName);
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }
    
  //  @RequestMapping(value = "/{authorizationUrl}",method=RequestMethod.DELETE)
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "Employee details with id - " + employeeId + " deleted";
    }

    @GetMapping("/employees/search/{firstName}")
    public List<Employee> searchEmployeesByFirstName(@PathVariable String firstName) {
        return employeeService.searchEmployeeByFirstName(firstName);
    }

    @GetMapping("/employees/sort")
    public List<Employee> sortEmployeesInOrder(@RequestParam("order") String order) {
        return employeeService.sortEmployeeByOrder(order);
    }

}