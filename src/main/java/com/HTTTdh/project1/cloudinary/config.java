package com.HTTTdh.project1.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", System.getenv("cloud_name"),
                "api_key", System.getenv("api_key"),
                "api_secret", System.getenv("api_secret"),
                "secure", true
        ));
    }
}
