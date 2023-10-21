package com.prodemy.crudemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.prodemy.crudemo.model.Student;

public interface StudentService {
	// Get all students from database
	List<Student> getAllStudents();
	// Save new student
	void saveStudent(Student student);
	// Get student by nim
	Student getStudentByNim(long nim);
	// Delete student by nim
	void deleteStudentByNim(long nim);
	Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
