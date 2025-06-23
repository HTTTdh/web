package com.HTTTdh.project1.controllers.api;

import com.HTTTdh.project1.DTO.CommentDTO;
import com.HTTTdh.project1.models.Comment;
import com.HTTTdh.project1.models.User;
import com.HTTTdh.project1.security.services.CommentService;
import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserDetailsServiceImpl userService;
    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO, @RequestParam Long postId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userService.findByUsername(username);
        Comment comment = commentDTO.toEntity();
        comment.setAuthor(author);
        Comment savedComment = commentService.saveComment(comment, postId);
        return ResponseEntity.ok(CommentDTO.fromEntity(savedComment));
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Long postId) {
        Boolean status = commentService.deleteCommentById(postId);
        if (status) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.ok("Deletion failed");
    }
    }
