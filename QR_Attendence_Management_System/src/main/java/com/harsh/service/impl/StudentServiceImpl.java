package com.harsh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.exceptions.ResourceNOtFoundException;
import com.harsh.model.Student;
import com.harsh.repository.StudentRepo;
import com.harsh.service.QRCodeService;
import com.harsh.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private QRCodeService qrCodeService;

// Save Student
	@Override
	public Student saveStudent(Student student) {
		
		return studentRepo.save(student);
	}

//Get student by id
	@Override
	public Optional<Student> getStudentById(int id) {
		return studentRepo.findById(id);
	
	}

//Update  Student
	@Override
	public Optional<Student> updateStudent(Student student,int id) {
		Student student2 = studentRepo.findById(id).orElseThrow(() ->new ResourceNOtFoundException("Student", "id", id));
		student2.setName(student.getName());
		student2.setRollNumber(student.getRollNumber());
		student2.setEmail(student.getEmail());
		student2.setQrCodeUrl(student.getQrCodeUrl());
		Student save = studentRepo.save(student2);
		return Optional.of(save);
	}

//Get All Student
	@Override
	public List<Student> getAllStudent() {
		return	studentRepo.findAll();
	}

//Delete Student
	@Override
	public void deleteStudent(int id) {
		studentRepo.deleteById(id);

	}
// generateStudentQRCode
	@Override
	public byte[] generateStudentQRCode(int id) {
		 Student student = studentRepo.findById(id).orElseThrow(() ->new ResourceNOtFoundException("Student", "id", id));
	        return qrCodeService.generateQRCode(student);
	}

}
