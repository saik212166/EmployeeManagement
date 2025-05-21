package com.example.EmployeeManagement.Service;

import com.example.EmployeeManagement.Repository.RepositoryEmployee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmployeeManagement.Model.Employee;
@Service
public class EmployeeService {

    @Autowired
    private RepositoryEmployee repo;
    
    public Employee createEmployee(Employee employee) {
        if (repo.findByEmail(employee.getEmail()).isPresent()) {
            throw new DuplicateEmailException("Email already exists: " + employee.getEmail());
        }
        return repo.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee updateEmployee(int id, Employee updatedEmp) {
        Employee emp = getEmployeeById(id);
        emp.setName(updatedEmp.getName());
        emp.setEmail(updatedEmp.getEmail());
        emp.setDepartment(updatedEmp.getDepartment());
        emp.setSalary(updatedEmp.getSalary());
        return repo.save(emp);
    }

    public void deleteEmployee(int id) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id " + id);
        }
        repo.deleteById(id);
    }

    public void deleteAllEmployee(){
        repo.deleteAll();
    }
   
}