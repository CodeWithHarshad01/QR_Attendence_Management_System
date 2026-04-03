package com.harsh.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.exceptions.ResourceNOtFoundException;
import com.harsh.model.LeaveRequest;
import com.harsh.model.Student;
import com.harsh.repository.LeaveRequestRepo;
import com.harsh.repository.StudentRepo;
import com.harsh.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
	@Autowired
	private LeaveRequestRepo requestRepo;
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public LeaveRequest createLeaveRequest(LeaveRequest request, int sid) {
		Optional<Student> optional = studentRepo.findById(sid);
		if (optional.isEmpty()) {
			throw new ResourceNOtFoundException("Student", "id", sid);
		}
		Student student = optional.get();
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setStudent(student);
		leaveRequest.setStartDate(request.getStartDate());
		leaveRequest.setEndDate(request.getEndDate());
		leaveRequest.setReason(request.getReason());
		leaveRequest.setStatus(request.getStatus());
		leaveRequest.setApprovedBy(request.getApprovedBy());
		return requestRepo.save(leaveRequest);
	}

	@Override
	public Optional<LeaveRequest> getLeaveRequestById(int id) {
		LeaveRequest leaveRequest = requestRepo.findById(id)
				.orElseThrow(() -> new ResourceNOtFoundException("LeaveRequest", "id", id));
		return Optional.of(leaveRequest);
	}

	@Override
	public LeaveRequest updateLeaveRequest(LeaveRequest leaveRequest, int id) {
		LeaveRequest leaveRequest1 = requestRepo.findById(id)
				.orElseThrow(() -> new ResourceNOtFoundException("LeaveRequest", "id", id));
		leaveRequest1.setStartDate(leaveRequest.getStartDate());
		leaveRequest1.setEndDate(leaveRequest.getEndDate());
		leaveRequest1.setReason(leaveRequest.getReason());
		leaveRequest1.setStatus(leaveRequest.getStatus());
		leaveRequest1.setApprovedBy(leaveRequest.getApprovedBy());
		LeaveRequest save = requestRepo.save(leaveRequest1);
		return save;
	}

	@Override
	public List<LeaveRequest> getAllLeaveRequest() {
		return requestRepo.findAll();
	}

	@Override
	public void deleteLeaveRequest(int id) {
		requestRepo.deleteById(id);
	}

}
