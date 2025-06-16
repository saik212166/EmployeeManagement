package com.example.EmployeeManagement.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.createEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void deleteAll() {
        service.deleteAllEmployee();
    }
}
