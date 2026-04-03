package com.harsh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.model.Attendance;
import com.harsh.model.Student;

public interface AttendenceRepo extends JpaRepository<Attendance, Integer> {

	 List<Attendance> findByStudent(Student student);
	    boolean existsByStudentAndDate(Student student, String date);
}