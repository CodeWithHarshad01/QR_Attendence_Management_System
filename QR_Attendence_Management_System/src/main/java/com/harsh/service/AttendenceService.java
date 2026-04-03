package com.harsh.service;

import java.util.List;
import java.util.Optional;

import com.harsh.model.Attendance;

public interface AttendenceService {
	
	public Optional<Attendance> getAttendenceById(int id);
	
	public List<Attendance> getAllAttendence();
	
	public Optional<Attendance> updateAttendence(Attendance attendance,int id);
	
	public void deleteAttendence(int id);
	
	public Attendance markAttendanceFromQR(String qrCodeData);
	  
	

}
