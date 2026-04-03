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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.exceptions.ResourceNOtFoundException;
import com.harsh.model.Attendance;
import com.harsh.service.AttendenceService;
import com.harsh.service.LeaveRequestService;

@RestController
public class AttendenceController {

	@Autowired
	private AttendenceService service;
	@Autowired
	private LeaveRequestService leaveRequestService;

	@GetMapping("/getAttendenceById/{id}")
	public Optional<Attendance> getAttendenceById(@PathVariable int id) {
		Attendance attendenceById = service.getAttendenceById(id)
				.orElseThrow(() -> new ResourceNOtFoundException("Attendence", "id", id));
		return Optional.of(attendenceById);

	}

	@GetMapping("/getAllAtendence")
	public List<Attendance> getAllAtendence() {
		List<Attendance> list = service.getAllAttendence();
		return list;
	}

	@PutMapping("updateAttendence/{id}")
	public Optional<Attendance> updateAttendence(@RequestBody Attendance attendance, @PathVariable int id) {
		Optional<Attendance> updateAttendence = service.updateAttendence(attendance, id);
		return updateAttendence;
	}

	@DeleteMapping("/deleteAttendence/{id}")
	public ResponseEntity<String> deleteAttendence(@PathVariable int id) {
		Optional<Attendance> optional = service.getAttendenceById(id);
		if (optional.isPresent()) {
			service.deleteAttendence(id);
			return ResponseEntity.ok("Attendence deleted Succesfully...");
		} else {
			return ResponseEntity.status(404).body("Attendence not found with id:" + id);
		}
	}

//
	@PostMapping("/markAttendance")
	public ResponseEntity<?> markAttendance(@RequestParam String qrCodeData) {
		try {
			Attendance attendance = service.markAttendanceFromQR(qrCodeData);
			return ResponseEntity.ok(attendance);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("An unexpected error occurred");
		}
	}

}
