package com.reactspring.demobackend1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactspring.demobackend1.exception.ResourceNotFoundException;
import com.reactspring.demobackend1.model.Student;
import com.reactspring.demobackend1.repository.StudentRepository;

// REST API FOR CRUD OPERATIONS ( CREATE, RETREIVE, UPDATE, DELETE)

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/") // lives at http://localhost:3000/api/vi/
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository; // instance of repo to use operations in interface
	
	// get all employees 
	@GetMapping("/students")// lives at lives at  --http://localhost:3000/api/vi/students
	public List<Student> getAllStudents(){
		return studentRepository.findAll(); // returns all students in database as a list!
	}
	
	// create Student rest api
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	// get student by id rest api
	@GetMapping("/students/{id")
	public ResponseEntity <Student> getStudentById(@PathVariable Long id){
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
		return ResponseEntity.ok(student);
	}
	
	//update student
	@PutMapping("/students/{id}")
	public ResponseEntity <Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
		
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
		
		student.setFirstName(studentDetails.getFirstName());// call stters and getters from student class
		student.setLastName(studentDetails.getLastName());
		student.setEmailId(studentDetails.getEmailId());
		
		Student updateStudent = studentRepository.save(student);
		
		return ResponseEntity.ok(updateStudent);
		
	}
	
	// delete student rest api
	@DeleteMapping("/students/{id}")
	public ResponseEntity <Map <String, Boolean >> deleteStudent(@PathVariable Long id){
		
		Student student = studentRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Student does not exist with id: " + id));
		
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<> ();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}

}
