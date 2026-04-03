package com.harsh.exceptions;

import lombok.Data;

@Data
public class ResourceNOtFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNOtFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s :%s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
