package com.HTTTdh.project1.controllers;

import com.HTTTdh.project1.models.Comment;
import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.security.services.CommentService;
import com.HTTTdh.project1.security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostControllerUI {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/blog-detail")
    public String showBlogDetail(@RequestParam("title") String title, Model model) {
        System.out.println(title);
        Post post = postService.getByTitle(title);
        System.out.println(post.getId());
        List<Comment> comments = commentService.getCommentByPostId(post.getId());
        model.addAttribute("comments", comments.isEmpty() ? null : comments);
        model.addAttribute("post", post);
        return "blog-detail";
    }

    @GetMapping("/updatePost")
    public String updatePost(@RequestParam("title") String title, Model model) {
        Post post = postService.getByTitle(title);
        model.addAttribute("post", post);
        return "blog-edit";
    }

    @GetMapping("/addPost")
    public String addPost(){
        return "write";
    }
}
