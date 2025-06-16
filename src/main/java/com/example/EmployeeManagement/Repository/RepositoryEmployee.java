package com.example.EmployeeManagement.Repository;

import com.example.EmployeeManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositoryEmployee extends JpaRepository<Employee, Integer> {
    
    Optional<Employee> findByEmail(String email); 
}
