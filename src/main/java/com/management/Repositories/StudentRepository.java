package com.management.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.Entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,String>{
    Optional<Student> findByEmail(String email);
}
