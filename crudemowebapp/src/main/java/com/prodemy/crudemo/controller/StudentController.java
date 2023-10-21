package com.prodemy.crudemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prodemy.crudemo.model.Student;
import com.prodemy.crudemo.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	// These are method handlers
	
	// Display list of students
	// @RequestMapping
	@GetMapping("/") // Method handler for showing home page
	public String viewHomePage(Model model) {
		// model.addAttribute("listStudents", studentService.getAllStudents());
		// return "index";
		return findPaginated(1, "nim", "asc", model);
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		// Create model attribute to bind form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "new_student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
		//Save student to database
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{nim}")
	public String showFormForUpdate(@PathVariable (value="nim") long nim, Model model) {
		// Get student from the service
		Student student = studentService.getStudentByNim(nim);
		// Set student as a model attribute to pre-populate the form
		model.addAttribute("student", student);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{nim}")
	public String deleteStudent(@PathVariable (value="nim") long nim) {
		// Call delete student method
		this.studentService.deleteStudentByNim(nim);
		return "redirect:/";
	}
	
	// /page/1?sortField=nim&sortDir=asc
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) 
	{
		// View total in a single page
		int pageSize = 5;
		// Get page content
		Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> listStudents = page.getContent();
		
		// Show current page, total page, total items (pageSize), amd list of students
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		// Show sort field and direction
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		// Show students
		model.addAttribute("listStudents", listStudents);
		return "index";
	}
	
}
