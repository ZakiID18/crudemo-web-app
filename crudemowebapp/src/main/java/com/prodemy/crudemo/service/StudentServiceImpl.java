package com.prodemy.crudemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.prodemy.crudemo.model.Student;
import com.prodemy.crudemo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired // Wiring StudentRepository class to make a new data type
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getAllStudents() {
		// Will return the student list
		return studentRepository.findAll();
	}
	
	@Override
	public void saveStudent(Student student) {
		// Implement save student
		this.studentRepository.save(student);
	}

	@Override
	public Student getStudentByNim(long nim) {
		// Implement get student by nim
		Optional<Student> optional = studentRepository.findById(nim);
		Student student = null;
		if(optional.isPresent()) 
		{
			student = optional.get();
		}
		else 
		{
			throw new RuntimeException("Student not found for id: " + nim);
		}
		return student;
	}

	@Override
	public void deleteStudentByNim(long nim) {
		// Implement delete student
		this.studentRepository.deleteById(nim);
	}

	@Override
	public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// Implement pagiantion
		// Check if the sort is asc or desc
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		// Create pagination and sorting
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.studentRepository.findAll(pageable);
	}
}
