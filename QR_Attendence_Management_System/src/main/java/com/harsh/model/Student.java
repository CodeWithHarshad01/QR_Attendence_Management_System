package com.harsh.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;

	private String name;

	private String rollNumber;

	private String email;

	private String qrCodeUrl;

	// One student can have many attendance records
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Attendance> attendanceList;
	
	// One student can have many leave records
		@OneToMany(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true)
		@JsonIgnore
		private List<LeaveRequest> leaveReqList;

		public int getSid() {
			return sid;
		}

		public void setSid(int sid) {
			this.sid = sid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRollNumber() {
			return rollNumber;
		}

		public void setRollNumber(String rollNumber) {
			this.rollNumber = rollNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getQrCodeUrl() {
			return qrCodeUrl;
		}

		public void setQrCodeUrl(String qrCodeUrl) {
			this.qrCodeUrl = qrCodeUrl;
		}

		public List<Attendance> getAttendanceList() {
			return attendanceList;
		}

		public void setAttendanceList(List<Attendance> attendanceList) {
			this.attendanceList = attendanceList;
		}

		public List<LeaveRequest> getLeaveReqList() {
			return leaveReqList;
		}

		public void setLeaveReqList(List<LeaveRequest> leaveReqList) {
			this.leaveReqList = leaveReqList;
		}

		
}
