package com.HTTTdh.project1.controllers;

import com.HTTTdh.project1.DTO.PostDTO;
import com.HTTTdh.project1.models.Post;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.security.services.PostService;
import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userService.findByUsername(username);
        Post post = postDTO.toEntity();
        post.setAuthor(author);
        Post savedPost = postService.save(post);
        return ResponseEntity.ok(PostDTO.fromEntity(savedPost));
    }
    @RequestMapping("/list")
    public ResponseEntity<?> listPosts() {
        List<Post> posts = postService.getAll();
        List<PostDTO> postDTOs= new ArrayList<>();
        for (Post post : posts) {
            PostDTO dto = PostDTO.fromEntity(post);
            postDTOs.add(dto);
        }
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/myPost")
    public ResponseEntity<?> getPost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userService.findByUsername(username);
        List<Post> posts = postService.getPosts(author);
        List<PostDTO> postDTOs= new ArrayList<>();
        for (Post post : posts) {
            PostDTO dto = PostDTO.fromEntity(post);
            postDTOs.add(dto);
        }
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/myPostByTitle")
    public ResponseEntity<?> getPostById(@RequestParam("title") String title) {
        Post post = postService.getByTitle(title);
        return ResponseEntity.ok(PostDTO.fromEntity(post));
    }

    @PostMapping("/updatePost")
    public ResponseEntity<?> updatePost(@RequestParam Long id, @RequestBody PostDTO postDTO) {
        Post post = postDTO.toEntity();
        Post savedPost = postService.updatePost(post, id);
        return ResponseEntity.ok(PostDTO.fromEntity(savedPost));
    }
    @PostMapping("/deletePost")
    public ResponseEntity<?> deletePost(@RequestParam Long postId) {
        Boolean status = postService.deletePostById(postId);
        if (status) {
            return ResponseEntity.ok("Post deleted successfully");
        }
        return ResponseEntity.ok("Post deletion failed");
    }
}
