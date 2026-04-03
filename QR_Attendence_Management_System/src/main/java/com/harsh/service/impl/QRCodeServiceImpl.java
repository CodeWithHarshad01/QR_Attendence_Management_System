package com.harsh.service.impl;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.harsh.model.Student;
import com.harsh.service.QRCodeService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public byte[] generateQRCode(Student student) {
        try {
            String qrContent = generateQRContent(student);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 250, 250);
            
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }

    @Override
    public String generateQRContent(Student student) {
        return String.format("ATTENDANCE:%d:%s", student.getSid(), student.getRollNumber());
    }
}