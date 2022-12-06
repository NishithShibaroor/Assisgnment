package com.example.micro.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.micro.service.model.Students;
import com.example.micro.service.repository.StudentsRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {
	@Autowired
	private StudentsRepository studentsRepository;

	@GetMapping("/students")
	public List<Students> getAllStudents() {
		return studentsRepository.findAll();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Students> getStudentsById(@PathVariable(value = "id") Long studentId) throws Exception {
		Students Students = studentsRepository.findById(studentId)
		.orElseThrow(() -> new Exception("Student not found for this id :: " + studentId));
		return ResponseEntity.ok().body(Students);
	}

	@PostMapping("/students")
	public Students createStudents(@Valid @RequestBody Students Students) {
		return studentsRepository.save(Students);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Students> updateStudents(@PathVariable(value = "id") Long studentId,
			@Valid @RequestBody Students StudentsDetails) throws Exception {
		Students students = studentsRepository.findById(studentId)
		.orElseThrow(() -> new Exception("Student not found for this id :: " + studentId));

		students.setStudentName(StudentsDetails.getStudentName());
		students.setAddress(StudentsDetails.getAddress());
		final Students updatedStudents = studentsRepository.save(students);
		return ResponseEntity.ok(updatedStudents);
	}

	@DeleteMapping("/students/{id}")
	public Map<String, Boolean> deleteStudents(@PathVariable(value = "id") Long studentId) throws Exception{
		Students Students = studentsRepository.findById(studentId)
		.orElseThrow(() -> new Exception("Student not found for this id :: " + studentId));

		studentsRepository.delete(Students);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
