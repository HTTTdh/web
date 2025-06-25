package com.HTTTdh.project1.controllers;

import com.HTTTdh.project1.DTO.PostDTO;
import com.HTTTdh.project1.security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String home(Model model) {
        List<PostDTO> postDTOs = postService.getAll().stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());
        model.addAttribute("featuredPosts", postDTOs);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/chatbot")
    public String chatbot() {
        return "chatbot";
    }
}
