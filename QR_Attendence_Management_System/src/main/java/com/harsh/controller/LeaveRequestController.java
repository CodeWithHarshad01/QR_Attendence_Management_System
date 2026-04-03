package com.harsh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.model.LeaveRequest;
import com.harsh.model.Student;
import com.harsh.service.LeaveRequestService;

@RestController
public class LeaveRequestController {
	@Autowired
	private LeaveRequestService leaveRequestService;

	@PostMapping("/createLeaveRequest/{sid}")
	public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest request, @PathVariable int sid) {
		LeaveRequest leaveRequest = leaveRequestService.createLeaveRequest(request, sid);
		return leaveRequest;
	}

	@GetMapping("/getLeaveRequestById/{id}")
	public Optional<LeaveRequest> getLeaveRequestById(@PathVariable int id) {
		Optional<LeaveRequest> optional = leaveRequestService.getLeaveRequestById(id);
		return optional;
	}

	@PutMapping("/updateLeaveRequest/{id}")
	public LeaveRequest updateLeaveRequest(@RequestBody LeaveRequest leaveRequest, @PathVariable int id) {
		LeaveRequest updateLeaveRequest = leaveRequestService.updateLeaveRequest(leaveRequest, id);
		return updateLeaveRequest;
	}

	@GetMapping("/getAllLeaveRequest")
	public List<LeaveRequest> getAllLeaveRequest() {
		List<LeaveRequest> allLeaveRequest = leaveRequestService.getAllLeaveRequest();
		return allLeaveRequest;
	}
	
	@DeleteMapping("/deleteLeaveRequest/{id}")
	public ResponseEntity<String> deleteLeaveRequest(@PathVariable int id) {
		 Optional<LeaveRequest> leaveRequestById = leaveRequestService.getLeaveRequestById(id);
		if (leaveRequestById.isPresent()) {
			leaveRequestService.deleteLeaveRequest(id);
			return ResponseEntity.ok("leaveRequest deleted Succesfully...");
		} else {
			return ResponseEntity.status(404).body("leaveRequest not found with id:" + id);
		}
	}

}
