package com.HTTTdh.project1.controllers;

import com.HTTTdh.project1.DTO.PostDTO;
import com.HTTTdh.project1.DTO.UserDTO;
import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.payload.response.MessageResponse;
import com.HTTTdh.project1.security.services.PostService;
import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserControllerUI {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    PostService postService;
    @GetMapping("/profile")
    public String findUserByUsername(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userDetailsServiceImpl.findByUsername(username);
        List<Post> posts = postService.getPosts(author);
        model.addAttribute("posts", posts);
        model.addAttribute("user", author);
        return "profile";
    }
    @GetMapping("/updateProfile")
    public String updateProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userDetailsServiceImpl.findByUsername(username);
        model.addAttribute("user", author);
        return "profile-edit";
    }
}
