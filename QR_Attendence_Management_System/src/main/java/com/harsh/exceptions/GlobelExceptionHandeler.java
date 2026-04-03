package com.harsh.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobelExceptionHandeler {
	
	@ExceptionHandler(ResourceNOtFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandeler(ResourceNOtFoundException ex) {
		String message = ex.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false);
		return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public  ResponseEntity<Map<String, String>> handelMethodArgsNotValidExceptionmethod(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String FieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
		resp.put(FieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ApiExcepton.class)
	public ResponseEntity<ApiResponce> handelApiException(ApiExcepton ex) {
		String message = ex.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, true);
		return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.BAD_REQUEST);

	}

}
