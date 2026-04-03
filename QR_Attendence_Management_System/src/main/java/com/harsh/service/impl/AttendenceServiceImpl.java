package com.harsh.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.exceptions.ResourceNOtFoundException;
import com.harsh.model.Attendance;
import com.harsh.model.Status;
import com.harsh.model.Student;
import com.harsh.repository.AttendenceRepo;
import com.harsh.repository.StudentRepo;
import com.harsh.service.AttendenceService;

@Service
public class AttendenceServiceImpl implements AttendenceService {

	@Autowired
	private AttendenceRepo attendenceRepo;
	@Autowired
	private StudentRepo repo;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

//get attendance by id
	@Override
	public Optional<Attendance> getAttendenceById(int id) {
		return attendenceRepo.findById(id);

	}

//get all attendance
	@Override
	public List<Attendance> getAllAttendence() {
		return attendenceRepo.findAll();
	}

//update attendance
	@Override
	public Optional<Attendance> updateAttendence(Attendance attendance, int id) {
		Attendance attendance2 = attendenceRepo.findById(id)
				.orElseThrow(() -> new ResourceNOtFoundException("Attendence", "id", id));
		attendance2.setDate(LocalDate.now().toString());
		attendance2.setTime(LocalTime.now().toString());
		attendance2.setStatus(attendance.getStatus());
		Attendance save = attendenceRepo.save(attendance2);
		return Optional.of(save);
	}

// delete attendance
	@Override
	public void deleteAttendence(int id) {
		attendenceRepo.deleteById(id);
	}

//Mark Attendance From QR code
	@Override
	public Attendance markAttendanceFromQR(String qrCodeData) {
		String[] parts = qrCodeData.split(":");
		if (parts.length != 3 || !parts[0].equals("ATTENDANCE")) {
			throw new IllegalArgumentException("Invalid QR code format");
		}

		int studentId = Integer.parseInt(parts[1]);
		String rollNumber = parts[2];

		Student student = repo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

		if (!student.getRollNumber().equals(rollNumber)) {
			throw new RuntimeException("QR code data mismatch");
		}

		String today = LocalDate.now().format(dateFormatter);
		boolean alreadyMarked = attendenceRepo.existsByStudentAndDate(student, today);

		if (alreadyMarked) {
			throw new RuntimeException("Attendance already marked today for student:" + student.getName());
		}

		Attendance attendance = new Attendance();
		attendance.setStudent(student);
		attendance.setDate(today);
		attendance.setTime(java.time.LocalTime.now().toString().substring(0, 5));
		attendance.setStatus(Status.Present);

		return attendenceRepo.save(attendance);
	}

}
