package com.HTTTdh.project1.controllers;

import com.HTTTdh.project1.DTO.UserDTO;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/")
    public ResponseEntity<?> findUserByUsername(@RequestParam String username) {
        User user = userDetailsServiceImpl.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found!"));
        }
        return ResponseEntity.ok(UserDTO.fromEntity(user));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updateUser = userDetailsServiceImpl.updateUser(user);
        return ResponseEntity.ok(UserDTO.fromEntity(updateUser));
    }
}
