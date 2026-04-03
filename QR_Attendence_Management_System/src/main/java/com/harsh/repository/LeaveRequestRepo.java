package com.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.model.LeaveRequest;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Integer> {

}
