package com.example.EmployeeManagement.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeManagement.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

