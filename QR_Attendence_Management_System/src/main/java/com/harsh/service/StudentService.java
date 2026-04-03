package com.harsh.service;

import java.util.List;
import java.util.Optional;

import com.harsh.model.Student;

public interface StudentService {

	public Student saveStudent(Student student);

	public Optional<Student> getStudentById(int id);

	public Optional<Student> updateStudent(Student student,int id);

	public List<Student> getAllStudent();

	public void deleteStudent(int id);
	
	public byte[] generateStudentQRCode(int id);

}
