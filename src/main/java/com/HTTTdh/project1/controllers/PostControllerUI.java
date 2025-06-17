package com.HTTTdh.project1.controllers;

import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostControllerUI {
    @Autowired
    private PostService postService;

    @GetMapping("/blog-detail")
    public String showBlogDetail(@RequestParam("title") String title, Model model) {
        Post post = postService.getByTitle(title);
        model.addAttribute("post", post);
        return "blog-detail";
    }
}
