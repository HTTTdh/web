package com.HTTTdh.project1.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploadService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        File uploadedFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(uploadedFile);

        Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());

        return (String) uploadResult.get("secure_url"); // Trả về URL ảnh
    }
}
