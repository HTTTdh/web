package com.HTTTdh.project1.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.security.services.PostService;
import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;

@Controller
public class AdminController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserDetailsServiceImpl userService;
    @GetMapping("/admin")
    public String admin(Model model) {
        System.out.println("admin");
        return "admin/homeAdmin";
    }

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<Post> posts = postService.getAll();
       
        model.addAttribute("posts", posts);
        return "admin/listArticle";
    }
    @GetMapping("/admin/users")
    public String users(Model model) {
        List<User> users = userService.getAll();
        
        model.addAttribute("users", users);
        return "admin/listUser";
    }
    @PostMapping("/admin/posts/{id}")
    public String acceptPost(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        post.setAccept(true);
        postService.save(post);
        return "redirect:/admin/posts";
    }

}
