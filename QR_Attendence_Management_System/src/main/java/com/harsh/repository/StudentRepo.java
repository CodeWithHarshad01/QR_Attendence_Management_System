package com.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
