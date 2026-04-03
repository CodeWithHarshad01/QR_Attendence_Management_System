package com.harsh.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.exceptions.ResourceNOtFoundException;
import com.harsh.model.Student;
import com.harsh.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService service;

//create user	
	@PostMapping("/createStudent")
	public Student createStudent(@RequestBody Student student) {
		Student saveStudent = service.saveStudent(student);
		return saveStudent;
	}

//get user
	@GetMapping("/getStudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable int id) throws AttributeNotFoundException {
		Student student = service.getStudentById(id)
				.orElseThrow(() -> new ResourceNOtFoundException("Student", "id", id));
		return Optional.of(student);
	}

//update Student	
	@GetMapping("/updateStudent/{id}")
	public Optional<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
		Optional<Student> updateStudent = service.updateStudent(student, id);
		return updateStudent;
	}

//get All Student	
	@GetMapping("/getAllStudent")
	public List<Student> getAllStudent() {
		List<Student> list = service.getAllStudent();
		return list;
	}

// delete student by id
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		Optional<Student> optional = service.getStudentById(id);
		if (optional.isPresent()) {
			service.deleteStudent(id);
			return ResponseEntity.ok("Student deleted Succesfully...");
		} else {
			return ResponseEntity.status(404).body("Student not found with id:" + id);
		}
	}

//get Student QR Code
	@GetMapping(value = "/{id}/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getStudentQRCode(@PathVariable int id) {
		try {
			byte[] qrCode = service.generateStudentQRCode(id);
			return ResponseEntity.ok(qrCode);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage().getBytes(StandardCharsets.UTF_8));
		}
	}
}
