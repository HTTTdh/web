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
                "cloud_name", "dzemhw9vp",
                "api_key", "834912327933738",
                "api_secret", "60vuoDv46qX4tnXIo5nGHmDQXy4",
                "secure", true
        ));
    }
}
