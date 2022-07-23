package com.greatlearning.employee_management.repositories;

import com.greatlearning.employee_management.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
}