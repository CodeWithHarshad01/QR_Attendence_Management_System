package com.harsh.service;

import com.harsh.model.Student;

public interface QRCodeService {

	 byte[] generateQRCode(Student student);
	    String generateQRContent(Student student);
}
