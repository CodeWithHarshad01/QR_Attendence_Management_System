package com.harsh.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce {
	private String message;
	private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ApiResponce(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public ApiResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
