package com.harsh.service;

import java.util.List;
import java.util.Optional;

import com.harsh.model.LeaveRequest;

public interface LeaveRequestService {
	public LeaveRequest createLeaveRequest(LeaveRequest request,int sid);
	
	public Optional<LeaveRequest> getLeaveRequestById(int id);
	
	public LeaveRequest updateLeaveRequest(LeaveRequest leaveRequest,int id);
	
	public List<LeaveRequest> getAllLeaveRequest();
	
	public void deleteLeaveRequest(int id);

}
