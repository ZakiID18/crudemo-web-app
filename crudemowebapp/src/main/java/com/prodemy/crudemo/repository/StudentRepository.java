package com.prodemy.crudemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodemy.crudemo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
